package game.messages.calls;

import game.utilities.Encoder;

public class Register extends Call {
    //register the user on the server side
    final short USER;
    public static final byte ID = -0b1111111; 
    final String HEADER;
    final String CONTENT;

    public Register(, short USER, String CONTENT) {
        this.CONTENT = CONTENT;
        this.HEADER = Encoder.encodeBytePair(ID, )
    }

    public String toString() {
        return this.HEADER+this.CONTENT;
    }

}