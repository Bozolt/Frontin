package game.messages.calls;

import game.utilities.Encoder;

public class Register implements  Call {
    //register the user on the server side
    final short USER;
    public static final short ID = Short.MIN_VALUE;
    final byte[] CONTENT;

    public Register(short USER, byte[] CONTENT) {
        this.USER = USER;
        this.CONTENT = CONTENT;
    }

    @Override 
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            Encoder.encodeShort(ID),
            Encoder.encodeShort(USER),
            CONTENT
        });
    }

}