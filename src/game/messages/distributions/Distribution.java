package game.messages.distributions;

import java.io.ByteArrayInputStream;
import game.utilities.DecodeableByteArrayInputStream;

public interface Distribution {

    public byte[] toBytes();

    public static Distribution fromBytes(byte[] bytes) throws Exception {
        DecodeableByteArrayInputStream stream = new DecodeableByteArrayInputStream(new ByteArrayInputStream(bytes));
        switch (stream.readByte()) {
            case Delete.ID:
                return new Delete(
                    stream.readShort(),
                    stream.readInt(),
                    stream.readLong()
                );

            case Disconnect.ID:
                return new Disconnect(
                    stream.readShort(),
                    stream.readLong()
                );

            case Patch.ID:
                return new Patch(
                    stream.readShort(),
                    stream.readInt(),
                    stream.readLong(),
                    stream.readAllBytes()
                );

            case Post.ID:
                return new Post(
                    stream.readShort(),
                    stream.readShort(),
                    stream.readInt(),
                    stream.readLong(),
                    stream.readAllBytes()
                );

            case Put.ID:
                return new Put(
                    stream.readShort(),
                    stream.readShort(),
                    stream.readInt(),
                    stream.readLong(),
                    stream.readAllBytes()
                );
        
            default:
                throw new IllegalArgumentException("Unknown or undefined Distribution type");
        }

    }


}
