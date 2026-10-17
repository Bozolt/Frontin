package game.messages.distributions;

import game.utilities.Encoder;

public class Patch implements Distribution {
    //Data replacer
    final short USER;
    final int SUBJECT;
    final short PTID;
    final short VTID;
    public static final byte ID = Byte.MIN_VALUE+4;
    final long timeStamp;
    final byte[] CONTENT;
    public Patch(short PackageableTypeID, short VariableTypeID, short USER, int SUBJECT, long timeStamp, byte[] CONTENT) {
        this.USER = USER;
        this.SUBJECT = SUBJECT;
        this.PTID = PackageableTypeID;
        this.VTID = VariableTypeID;
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
            Encoder.encodeShort(VTID),
            Encoder.encodeLong(timeStamp),
            CONTENT
        });
    }
}
