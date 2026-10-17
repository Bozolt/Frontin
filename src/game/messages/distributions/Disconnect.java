package game.messages.distributions;

import game.utilities.Encoder;

public class Disconnect implements  Distribution {
    //tells the sure to disconnect
    final short USER;
    final int SUBJECT;
    public static final byte ID = Byte.MIN_VALUE+2;
    final long timeStamp;
    public Disconnect(short USER, int SUBJECT, long timeStamp) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.timeStamp = timeStamp;
    }

    @Override
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            new byte[] {ID},
            Encoder.encodeShort(USER),
            Encoder.encodeInt(SUBJECT),
            Encoder.encodeLong(timeStamp)
        });
    }
}
