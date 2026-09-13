package game.messages.distributions;

import game.utilities.Encoder;

public class Disconnect extends Distribution {
    //tells the sure to disconnect
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111101;
    final String HEADER;
    public Disconnect(byte UC, short USER, int SUBJECT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.UC = UC;
        this.HEADER = Encoder.encodeBytePair(this.ID, this.UC)+""+Encoder.encodeShort(this.USER)+""+Encoder.encodeInt(this.SUBJECT);
    }

    public String toString() {
        return this.HEADER;
    }
}
