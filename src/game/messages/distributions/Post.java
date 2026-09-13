package game.messages.distributions;

import game.utilities.Encoder;
import game.utilities.elements.Element;

public class Post extends Distribution {
    //Data register
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111111;
    final String HEADER;
    public final String CONTENT;
    public Post(byte UC, short USER, long timeStamp, int SUBJECT, String CONTENT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.UC = UC;
        this.HEADER = Encoder.encodeBytePair(this.ID, this.UC)+""+Encoder.encodeLong(timeStamp)+""+Encoder.encodeShort(this.USER)+""+Encoder.encodeInt(this.SUBJECT);
        this.CONTENT = CONTENT;
    }

    public String toString() {
        return this.HEADER+this.CONTENT;
    }
}
