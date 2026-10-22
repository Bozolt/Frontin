package game.utilities.streamables.networkables.distributions;

import game.utilities.Encoder;
import game.utilities.streamables.networkables.Networkable;

public class Put implements Networkable {
    //Data replacer
    final short USER;
    final int SUBJECT;
    //Packageable Type ID
    final short PTID;
    public static final byte ID = Byte.MIN_VALUE+3;
    final long timeStamp;
    final byte[] CONTENT;
    public Put(short PackageableTypeID, short USER, int SUBJECT, long timeStamp, byte[] CONTENT) {
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