package game.messages.distributions;

import game.utilities.Encoder;

public class Patch extends Distribution {
    //Data replacer
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111011;
    final long timeStamp;
    final String HEADER;
    final String CONTENT;
    public Patch(byte UC, short USER, int SUBJECT, long timeStamp, String CONTENT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.UC = UC;
        this.timeStamp = timeStamp;
        this.HEADER = Encoder.encodeBytePair(this.ID, this.UC)+""+Encoder.encodeShort(this.USER)+""+Encoder.encodeInt(this.SUBJECT)+""+Encoder.encodeLong(timeStamp);
        this.CONTENT = CONTENT;
    }

    public String toString() {
        return this.HEADER+this.CONTENT;
    }
}
