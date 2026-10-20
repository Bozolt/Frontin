package game.utilities.packageables.masks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import game.utilities.Encoder;
import game.utilities.packageables.Packageable;
import game.utilities.packageables.Point;

public final class OvalMask implements Mask, Packageable {

    private List<byte[]> patches = Collections.synchronizedList(new LinkedList<byte[]>());

    public void addPatch(byte[] patch) {patches.add(patch);}

    private double width, height;
    private boolean isCircle = false;
    private boolean isPoint = false;
        
    public OvalMask(double width, double height) {
        if (width <= 0) throw new IllegalArgumentException("The width of an OvalMask cannot be zero or less");
        if (height <= 0) throw new IllegalArgumentException("The height of an OvalMask cannot be zero or less");
        this.width = width;
        this.height = height;
    }

    public OvalMask(double radius) {
        if (radius <= 0) throw new IllegalArgumentException("The radius of a CircleMask cannot be zero or less");
        this.width = radius;
        this.height = radius;
        this.isCircle = true;
    }

    public OvalMask() {
        this.isPoint = true;
    }

    public OvalMask(double width, double height, boolean[] flags) {
        this.width = width;
        this.height = height;
        this.isCircle = flags[0];
        this.isPoint = flags[1];
    }

    @Override
    public Point[] getPoints() {
        if (isPoint) return new Point[] {};
        if (isCircle) return new Point[] {new Point(width, 0)};
        return new Point[] {new Point(width, 0), new Point(0, height)};
    }

    @Override
    public Mask getResized(float xScale, float yScale) {
        if (isPoint) return this;
        return new OvalMask(width*xScale, height*yScale);
    }

    @Override
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            Encoder.encodeDouble(width),
            Encoder.encodeDouble(height),
            new byte[] {Encoder.encode8Booleans(new boolean[] {isCircle, isPoint, false, false, false, false, false, false})}
        });
    }

    @Override 
    public int getPackageID() {return Packageable.Package.OvalMask.ordinal();}

    @Override
    public boolean executePatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executePatch'");
    }
}
