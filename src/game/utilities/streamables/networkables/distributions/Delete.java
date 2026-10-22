package game.utilities.streamables.networkables.distributions;

import game.utilities.Encoder;
import game.utilities.streamables.networkables.Networkable;

public class Delete implements Networkable {
    //Data remover
    final short USER;
    final int SUBJECT;
    public static final byte ID = Byte.MIN_VALUE+1;
    final long timeStamp;
    public Delete(short USER, int SUBJECT, long timeStamp) {
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
