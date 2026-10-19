package game.utilities;

import java.io.ByteArrayInputStream;

public class DecodeableByteArrayInputStream {
    
    private ByteArrayInputStream stream;

    public DecodeableByteArrayInputStream(ByteArrayInputStream stream) {
        this.stream = stream;
    }

    public short readShort() throws Exception {
        return Encoder.decodeShort(stream.readNBytes(2));
    }

    public int readInt() throws Exception {
        return Encoder.decodeInt(stream.readNBytes(4));
    }

    public long readLong() throws Exception {
        return Encoder.decodeLong(stream.readNBytes(8));
    }

    public float readFloat() throws Exception {
        return Encoder.decodeFloat(stream.readNBytes(4));
    }

    public double readDouble() throws Exception {
        return Encoder.decodeDouble(stream.readNBytes(8));
    }

    public int available() {
        return stream.available();
    }

    public byte[] readNBytes(int n) throws Exception {
        return stream.readNBytes(n);
    }

    public byte readByte() throws Exception {
        return ((byte)(stream.read()-128));
    }

    public byte[] readAllBytes() throws Exception {
        return stream.readAllBytes();
    }

    public boolean readBoolean() throws Exception {
        return Encoder.decodeBoolean((byte)(stream.read()-128));
    }

    public boolean[] read8Boolean() throws Exception {
        return Encoder.decode8Booleans((byte)(stream.read()-128));
    }

}
