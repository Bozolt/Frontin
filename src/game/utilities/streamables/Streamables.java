package game.utilities.streamables;

import game.utilities.Encoder;
import game.utilities.streamables.reconstructables.Reconstructable;

public enum Streamables {

    Register(),


    Delete(),
    Disconnect(),
    Patch(),
    Post(),
    Put()
    ;

    //0 lengths means it's dynamically sized and it's runtime size will be declared inside it's byte array
    public final int byteLength;
    public final byte[] bytesID;
    public final boolean dynamicLength;

    public short getID() {return (short)this.ordinal();}

    private Streamables(int byteLength) {
        if (byteLength <= 0) throw new IllegalArgumentException("Packet size cannot be negative or zero");
        this.dynamicLength = false;
        this.byteLength = byteLength;
        this.bytesID = Encoder.encodeShort(getID());
    }

    private Streamables() {
        this.dynamicLength = true;
        this.byteLength = 0;
        this.bytesID = Encoder.encodeShort(getID());
    }

}
