package game.utilities.streamables.packageables;

import game.utilities.Encoder;
import game.utilities.streamables.Streamables;
import game.utilities.streamables.reconstructables.Reconstructable;

public interface Packageable {

    public byte[] toBytes();
    
    /*
    public static byte[] createPatch(short streamableID, short variableID, Object value) {
        //patch only support replacing values of a variable completely even if the variable stores Objects not primitives
        //Accounting for Patch Types can help incase a variable can be changed through many different methods
        //like numbers could be set to a completely new value or could be increased by an amount from it's previous value

        if (Streambles.values()[streamableID].type.Variable[variableID].object) {

            if (value ==null) throw new IllegalArgumentException("Value cannot be null");

            if (value instanceof Packageable) {
                Packageable packageable = ((Packageable)value);

                return Encoder.concatArrays(new byte[][] {
                Streamables.values()[packageableID].bytes,
                Streamables.Variable.values()[variableID].bytes,
                Streamables.values()[packageable.getPackageID()].bytes,
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

    public int getPackageID();

    */

}
