package game.messages.calls;

import java.util.Arrays;

import game.utilities.Encoder;

public interface Call {

    public byte[] toBytes();

    public static Call fromBytes(byte[] bytes) {
        switch (Encoder.decodeShort(bytes[0], bytes[1])) {
            case Register.ID:
                return new Register(
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 2, 4)),
                    Arrays.copyOfRange(bytes, 4, bytes.length)
                );
        
            default:
                throw new IllegalArgumentException("Unknown or undefined Call type");
        }
    }
}
