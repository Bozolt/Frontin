package game.utilities.packageables;

import game.utilities.Encoder;

public enum Package {

    Element(),
    StaticGraphic(),
    DynamicGraphic(),
    Point(),
    PolygonMask(),
    OvalMask()
    ;
    
    private class HiddenCounter {
        private static short count = Short.MIN_VALUE;
    }

    public final byte[] ID;

    private Package() {
        this.ID = Encoder.encodeShort(HiddenCounter.count);
        HiddenCounter.count ++;
    }

    public String toString() {
        return String.valueOf(ID);
    }

}
