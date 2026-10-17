package game.utilities.packageables;

import game.utilities.Encoder;

public enum Variable {
    //masterclass that holds all variable identifiers in a static context
    //these are purely for reference at the encoding/decoding stage, any Object implementing Packageable can and will by default
    //still ignore any id

    //A general scope for the number of variables should be all 16 bit short values

    

    SourceKey(),
    AssetKey(),
    Layer(),
    Visible(),
    Position(),
    Group(),
    ImageXScale(),
    ImageYScale(),
    Mask(),
    PreviousSpriteUpdate(),
    ImageIndex(),
    ImageSpeed(),
    X(),
    Y(),
    Points(),
    Width(),
    Height(),
    Circle(),
    Point()
    ;

    private class HiddenCounter {
        private static short count = Short.MIN_VALUE;
    }

    public final byte[] ID;

    private Variable() {
        this.ID = Encoder.encodeShort(HiddenCounter.count);
        HiddenCounter.count ++;
    }

    public String toString() {
        return String.valueOf(ID);
    }

}
