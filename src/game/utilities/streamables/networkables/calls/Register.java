package game.utilities.streamables.networkables.calls;

import game.utilities.Encoder;
import game.utilities.streamables.networkables.Networkable;

public class Register implements Networkable {
    //register the user on the server side
    final short USER;
    public static final short ID = Short.MIN_VALUE;
    final byte[] CONTENT;

    public Register(short USER, byte[] CONTENT) {
        this.USER = USER;
        this.CONTENT = CONTENT;
    }

    @Override 
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            Encoder.encodeShort(ID),
            Encoder.encodeShort(USER),
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