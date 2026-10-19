package game.messages.distributions;

import game.utilities.Encoder;

public class Patch implements Distribution {
    //Data replacer
    final short USER;
    final int SUBJECT;
    public static final byte ID = Byte.MIN_VALUE+4;
    final long timeStamp;
    final byte[] CONTENT;
    public Patch(short USER, int SUBJECT, long timeStamp, byte[] CONTENT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.timeStamp = timeStamp;
        this.CONTENT = CONTENT;
    }

    @Override
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            new byte[] {ID},
            Encoder.encodeShort(USER),
            Encoder.encodeLong(timeStamp),
            CONTENT
        });
    }
}
