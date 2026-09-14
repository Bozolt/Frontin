package game.messages.distributions;

import game.utilities.Encoder;

public class Disconnect extends Distribution {
    //tells the sure to disconnect
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111101;
    final long timeStamp;
    final String HEADER;
    public Disconnect(byte UC, short USER, int SUBJECT, long timeStamp) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.UC = UC;
        this.timeStamp = timeStamp;
        this.HEADER = Encoder.encodeBytePair(this.ID, this.UC)+""+Encoder.encodeShort(this.USER)+""+Encoder.encodeInt(this.SUBJECT)+""+Encoder.encodeLong(timeStamp);
    }

    public String toString() {
        return this.HEADER;
    }
}
