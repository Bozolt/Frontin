package game.messages.distributions;

import game.utilities.Encoder;

public class Delete extends Distribution {
    //Data remover
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111110;
    final long timeStamp;
    final String HEADER;
    public Delete(byte UC, short USER, int SUBJECT, long timeStamp) {
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
