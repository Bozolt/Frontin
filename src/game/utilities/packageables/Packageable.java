package game.utilities.packageables;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import game.utilities.DecodeableByteArrayInputStream;
import game.utilities.Encoder;
import game.utilities.packageables.elements.Element;
import game.utilities.packageables.elements.graphics.DynamicGraphic;
import game.utilities.packageables.elements.graphics.StaticGraphic;
import game.utilities.packageables.masks.OvalMask;
import game.utilities.packageables.masks.PolygonMask;

public interface Packageable {

    //threadsafe command line to queue changes to the Element
    public void addPatch(byte[] patch);

    public static enum Package {

        //byteLength variable provides how many bytes should be read to be converted into variable
        //0 is the designated for dynamic sized variables such as Masks that store their size inside their byte Array
        //dynamic sized classes are responsible in their own cases to read their own size

        Element(new Element().toBytes().length),
        StaticGraphic(new StaticGraphic().toBytes().length),
        DynamicGraphic(new DynamicGraphic().toBytes().length),
        Point(new Point().toBytes().length),
        OvalMask(new OvalMask().toBytes().length),
        PolygonMask(0),
        Points(0)
        ;

        public final int byteLength;
        public final byte[] bytes;

        private Package(int byteLength) {
            if (byteLength < 0) throw new IllegalArgumentException("Expected array cannot have negative length");
            this.byteLength = byteLength;
            this.bytes = Encoder.encodeInt(this.ordinal());
        }
    }

    public static enum Variable {
        //masterclass that holds all variable identifiers in a static context
        //these are purely for reference at the encoding/decoding stage, any Object implementing Packageable can and will by default
        //still ignore any id

        //byteLength variable provides how many bytes should be read to be converted into variable
        //0 is the designated for dynamic sized variables such as Masks that store their size inside their byte Array
        //dynamic sized variables are responsible in their own cases to read their own size when reconstructing
        KEY(Variable.intSize),
        SourceKey(Variable.intSize),
        AssetKey(Variable.longSize),
        Layer(Variable.shortSize),
        isVisible(Variable.boolSize),
        Position(Package.Point.byteLength, true),
        Group(Variable.shortSize),
        ImageXScale(Variable.shortSize),
        ImageYScale(Variable.shortSize),
        Mask(0, true),
        PreviousSpriteUpdate(Variable.longSize),
        ImageIndex(Variable.shortSize),
        ImageSpeed(Variable.floatSize),
        X(Variable.doubleSize),
        Y(Variable.doubleSize),
        Points(0, true),
        Width(Variable.doubleSize),
        Height(Variable.doubleSize),
        isCircle(Variable.boolSize),
        isPoint(Variable.boolSize)
        ;

        public static final int byteSize = 1, shortSize = 2, intSize = 4, longSize = 8, boolSize = 1, floatSize = 4, doubleSize = 8;

        public final int byteLength;
        public final byte[] bytes;
        public final boolean object;

        private Variable(int byteLength) {
            if (byteLength < 0) throw new IllegalArgumentException("Expected array cannot have negative length");
            this.byteLength = byteLength;
            this.bytes = Encoder.encodeInt(this.ordinal());
            this.object = false;
        }

        private Variable(int byteLength, boolean object) {
            if (byteLength < 0) throw new IllegalArgumentException("Expected array cannot have negative length");
            this.byteLength = byteLength;
            this.bytes = Encoder.encodeInt(this.ordinal());
            this.object = object;
        }

    }

    public static enum Patch {
        /*
            Any executePatch() method can choose to use the Patch types however it chooses to, their purpose is to show what kind of
            change is done to the value of the Variable but can be ignored aswell in the implementations
            Only one byte of space 128 values are designated for Patch Types as non should be unique to any one Classes implementation
            instead should be used under a more general scope
        */

        Modify,
        Replace
        ;

        public final byte byteID;

        private Patch() {
            this.byteID = (byte)this.ordinal();
        }

    }

    public static byte[] createPatch(int packageableID, int variableID, byte patchType, Object value) {
        //patch only support replacing values of a variable completely even if the variable stores Objects not primitives
        //Accounting for Patch Types can help incase a variable can be changed through many different methods
        //like numbers could be set to a completely new value or could be increased by an amount from it's previous value

        if (Variable.values()[variableID].object) {

            if (value ==null) throw new IllegalArgumentException("Value cannot be null");

            if (value instanceof Packageable) {
                Packageable packageable = ((Packageable)value);

                return Encoder.concatArrays(new byte[][] {
                Package.values()[packageableID].bytes,
                Variable.values()[variableID].bytes,
                {patchType},
                Package.values()[packageable.getPackageID()].bytes,
                packageable.toBytes()
            });
            
            } throw new IllegalArgumentException("Value cannot be non-Packageable");

        }
        else {

            byte[] holder = Encoder.encodeDynamically(value);

            if (holder == null) throw new IllegalArgumentException("Value cannot be null");

            return Encoder.concatArrays(new byte[][] {
                Encoder.encodeInt(packageableID),
                Encoder.encodeInt(variableID),
                holder
            });

        }

    }

    /*
        Server-side runs on multi-threaded solution where any number of threads calls update methods on Packageables and changes
        are staged for them
        Client-side only receives the change commands on the other hand which it stages in it's distributions list then on a single
        thread executes each command so it doesn't need a thread-safe implementation
        executePatch is a method implemented inside each Packageable and purposed to take the staged patches and apply them according
        to the classes own inner logics
        executePatch() methods are supposed to be implemented in a thread-safe manner where each class acceses only their own
        resources or other thread-safe resources
    */
    public boolean executePatch();
        

    public byte[] toBytes();

    public static Packageable fromBytes(byte[] bytes) {
        //master method for turning byte arrays back into the Packageable classes that produced them
        //For further documentation on each class see their toBytes method
        try {
            DecodeableByteArrayInputStream byteStream = new DecodeableByteArrayInputStream(new ByteArrayInputStream(bytes));
            switch (Package.values()[byteStream.readInt()]) {
                case Package.Element:
                    return new Element(
                        byteStream.readInt(),
                        byteStream.readInt(),
                        byteStream.readLong()
                    );

                case Package.StaticGraphic:
                    return new StaticGraphic(
                        byteStream.readInt(),
                        byteStream.readInt(),
                        byteStream.readLong(),
                        byteStream.readShort(),
                        byteStream.readBoolean(),
                        ((Point)Packageable.fromBytes(byteStream.readNBytes(Package.Point.byteLength))),
                        byteStream.readShort(),
                        Float.float16ToFloat(byteStream.readShort()),
                        Float.float16ToFloat(byteStream.readShort())
                    );

                case Package.DynamicGraphic:
                    return new DynamicGraphic(
                        byteStream.readInt(),
                        byteStream.readInt(),
                        byteStream.readLong(),
                        byteStream.readShort(),
                        byteStream.readBoolean(),
                        ((Point)Packageable.fromBytes(Encoder.concatArrays(new byte[][] {Encoder.encodeInt(Package.Point.ordinal()), byteStream.readNBytes(Package.Point.byteLength)}))),
                        byteStream.readShort(),
                        Float.float16ToFloat(byteStream.readShort()),
                        Float.float16ToFloat(byteStream.readShort()),
                        byteStream.readLong(),
                        byteStream.readShort(),
                        byteStream.readFloat()
                    );

                case Package.Point:
                    return new Point(
                        byteStream.readDouble(),
                        byteStream.readDouble()
                    );

                case Package.OvalMask:
                    return new OvalMask(
                        byteStream.readDouble(),
                        byteStream.readDouble(),
                        byteStream.read8Boolean()
                    );

                case Package.PolygonMask:
                    Point points[] = new Point[byteStream.readInt()];
                    byte[] marker = Encoder.encodeInt(Package.Point.ordinal());

                    for (int i = 0; i < points.length; i ++) {
                        points[i] = ((Point)Packageable.fromBytes(Encoder.concatArrays(new byte[][] {marker, byteStream.readNBytes(Package.Point.byteLength)})));
                    }

                    return new PolygonMask(
                        points
                    );
            
                default:
                    throw new IllegalArgumentException("Unknown class byteID or Unimplemented case");
            }
        } catch(Exception e) {e.printStackTrace(); return null;}
    }

    public int getPackageID();

}