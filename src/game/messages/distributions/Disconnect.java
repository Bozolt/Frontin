package game.messages.distributions;

import game.utilities.Encoder;

public class Disconnect implements  Distribution {
    //tells the sure to disconnect
    final short USER;
    public static final byte ID = Byte.MIN_VALUE+2;
    final long timeStamp;
    public Disconnect(short USER, long timeStamp) {
        this.USER = USER;
        this.timeStamp = timeStamp;
    }

    @Override
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            new byte[] {ID},
            Encoder.encodeShort(USER),
            Encoder.encodeLong(timeStamp)
        });
    }
}
