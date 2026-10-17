package game.messages.distributions;

import game.utilities.Encoder;

public class Put implements Distribution {
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
}