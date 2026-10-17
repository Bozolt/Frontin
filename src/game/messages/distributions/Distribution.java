package game.messages.distributions;

import java.util.Arrays;

import game.utilities.Encoder;

public interface Distribution {

    public byte[] toBytes();

    public static Distribution fromBytes(byte[] bytes) {
        
        switch (bytes[0]) {
            case Delete.ID:
                return new Delete(
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 1, 3)),
                    Encoder.decodeInt(Arrays.copyOfRange(bytes, 3, 7)),
                    Encoder.decodeLong(Arrays.copyOfRange(bytes, 7, 15))
                );

            case Disconnect.ID:
                return new Delete(
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 1, 3)),
                    Encoder.decodeInt(Arrays.copyOfRange(bytes, 3, 7)),
                    Encoder.decodeLong(Arrays.copyOfRange(bytes, 7, 15))
                );

            case Patch.ID:
                return new Patch(
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 7, 9)),
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 9, 11)),
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 1, 3)),
                    Encoder.decodeInt(Arrays.copyOfRange(bytes, 3, 7)),
                    Encoder.decodeLong(Arrays.copyOfRange(bytes, 11, 19)),
                    Arrays.copyOfRange(bytes, 19, bytes.length)
                );

            case Post.ID:
                return new Post(
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 7, 9)),
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 1, 3)),
                    Encoder.decodeInt(Arrays.copyOfRange(bytes, 3, 7)),
                    Encoder.decodeLong(Arrays.copyOfRange(bytes, 9, 17)),
                    Arrays.copyOfRange(bytes, 17, bytes.length)
                );

            case Put.ID:
                return new Put(
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 7, 9)),
                    Encoder.decodeShort(Arrays.copyOfRange(bytes, 1, 3)),
                    Encoder.decodeInt(Arrays.copyOfRange(bytes, 3, 7)),
                    Encoder.decodeLong(Arrays.copyOfRange(bytes, 9, 17)),
                    Arrays.copyOfRange(bytes, 17, bytes.length)
                );
        
            default:
                throw new IllegalArgumentException("Unknown or undefined Distribution type");
        }

    }


}
