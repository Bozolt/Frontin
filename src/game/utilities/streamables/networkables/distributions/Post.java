package game.utilities.streamables.networkables.distributions;

import game.utilities.Encoder;
import game.utilities.streamables.networkables.Networkable;

public class Post implements Networkable {
    //Data register
    final short USER;
    final int SUBJECT;
    //PackageableTypeID
    final short PTID;
    public static final byte ID = Byte.MIN_VALUE;
    final long timeStamp;
    public final byte[] CONTENT;
    public Post(short PackageableTypeID, short USER, int SUBJECT, long timeStamp, byte[] CONTENT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.PTID = PackageableTypeID;
        this.timeStamp = timeStamp;
        this.CONTENT = CONTENT;
    }

    @Override
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            new byte[] {ID},
            Encoder.encodeShort(USER),
            Encoder.encodeInt(SUBJECT),
            Encoder.encodeShort(PTID),
            Encoder.encodeLong(timeStamp),
            CONTENT
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
