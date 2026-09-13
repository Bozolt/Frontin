package game.messages.distributions;

import game.utilities.Encoder;
import game.utilities.elements.Element;

public class Delete extends Distribution {
    //Data remover
    final short USER;
    final int SUBJECT;
    final byte UC;
    public static final byte ID = -0b1111110;
    final String HEADER;
    public Delete(byte UC, short USER, int SUBJECT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.UC = UC;
        this.HEADER = Encoder.encodeBytePair(this.ID, this.UC)+""+Encoder.encodeShort(this.USER)+""+Encoder.encodeInt(this.SUBJECT);
    }

    public String toString() {
        return this.HEADER;
    }
}
