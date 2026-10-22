package game.utilities.streamables.networkables.distributions;

import game.utilities.Encoder;
import game.utilities.streamables.networkables.Networkable;

public class Disconnect implements Networkable {
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

    @Override
    public void addPatch(byte[] patch) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPatch'");
    }

    @Override
    public void executePatches() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executePatches'");
    }
}
