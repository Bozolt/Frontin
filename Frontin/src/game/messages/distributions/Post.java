package game.messages.distributions;

import game.utilities.Encoder;

public class Post extends Distribution {
    //Data register
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111111;
    final long timeStamp;
    final String HEADER;
    public final String CONTENT;
    public Post(byte UC, short USER, long timeStamp, int SUBJECT, String CONTENT) {
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
