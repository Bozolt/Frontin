package game.messages.distributions;

import game.utilities.Encoder;
import game.utilities.elements.Element;

public class Put extends Distribution {
    //Data replacer
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111100;
    final long timeStamp;
    final String HEADER;
    final String CONTENT;
    public Put(byte UC, short USER, int SUBJECT, long timeStamp, String CONTENT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.UC = UC;
        this.timeStamp = timeStamp;
        this.HEADER = Encoder.encodeBytePair(this.ID, this.UC)+""+Encoder.encodeShort(this.USER)+""+Encoder.encodeInt(this.SUBJECT)+""+Encoder.encodeLong(timeStamp);
        this.CONTENT = CONTENT;
    }

    public static String encodeContent() {
        return "";
    }

    public String toString() {
        return this.HEADER+this.CONTENT;
    }
}