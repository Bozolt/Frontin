package game.utilities;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class Encoder {
    private Encoder() {throw new UnsupportedOperationException("Utility class shouldn't be constructed");}

    public static byte[] encodeShort(short s) {
        //codes a short to two bytes
        return new byte[] {(byte)(s >> 8), (byte)s};
    }

    public static short decodeShort(byte a, byte b) {
        //decodes 2 bytes to a short
        
        return (short)((a & 0xFF) << 8 | (b & 0xFF));
    }

    public static short decodeShort(byte[] bytes) {
        if (bytes.length != 2) throw new IllegalArgumentException("Cannot decode more or less than 2 bytes into a short");

        return (short)((bytes[0] & 0xff) << 8 | (bytes[1] & 0xff));
    }

    public static byte[] encodeInt(int i) {
        //codes an integer to 4 bytes

        return new byte[] {(byte)(i >> 24), (byte)(i >> 16), (byte)(i >> 8), (byte)(i)};
    }

    public static int decodeInt(byte[] bytes) {
        //decodes 2 unicode characters to integers

        if (bytes.length != 4) throw new IllegalArgumentException("Cannot decode more or less than 4 bytes into an integer");
        
        return ((bytes[0] & 0xff) << 24 | (bytes[1] & 0xff) << 16 | (bytes[2] & 0xff) << 8 | (bytes[3] & 0xff));
    }

    public static byte[] encodeLong(long l) {
        //codes a long to 8 bytes

        return new byte[] {
            (byte)(l >> 56), (byte)(l >> 48), (byte)(l >> 40), (byte)(l >> 32), (byte)(l >> 24), (byte)(l >> 16), (byte)(l >> 8), (byte)(l)
        };
    }

    public static long decodeLong(byte[] bytes) {
        //decodes 8 bytes into a long

        if (bytes.length != 8) throw new IllegalArgumentException("Cannot decode more or less than 8 bytes into a long");

        return (
            (long)(bytes[0] & 0xff) << 56
            | (long)(bytes[1] & 0xff) << 48
            | (long)(bytes[2] & 0xff) << 40
            | (long)(bytes[3] & 0xff) << 32
            | (long)(bytes[4] & 0xff) << 24
            | (long)(bytes[5] & 0xff) << 16
            | (long)(bytes[6] & 0xff) << 8
            | (long)(bytes[7] & 0xff)
        );
    }

    public static byte[] encodeFloat(float f) {
        int i = Float.floatToIntBits(f);
        return new byte[] {(byte)(i >> 24), (byte)(i >> 16), (byte)(i >> 8), (byte)(i)};
    }

    public static float decodeFloat(byte[] bytes) {
        if (bytes.length != 4) throw new IllegalArgumentException("Cannot decode more or less than 4 bytes into an float");


        
        return Float.intBitsToFloat((bytes[0] & 0xff) << 24 | (bytes[1] & 0xff) << 16 | (bytes[2] & 0xff) << 8 | (bytes[3] & 0xff));
    }

    public static byte[] encodeDouble(double d) {
        //codes an double to 4 unicode characters

        long l = Double.doubleToLongBits(d);
        
        return new byte[] {
            (byte)(l >> 56), (byte)(l >> 48), (byte)(l >> 40), (byte)(l >> 32), (byte)(l >> 24), (byte)(l >> 16), (byte)(l >> 8), (byte)(l)
        };
    }

    public static double decodeDouble(byte[] bytes) {
        //decodes 4 unicode characters to double

        if (bytes.length != 8) throw new IllegalArgumentException("Cannot decode more or less than 8 bytes into a float");

        return Double.longBitsToDouble(
            (long)(bytes[0] & 0xff) << 56
            | (long)(bytes[1] & 0xff) << 48
            | (long)(bytes[2] & 0xff) << 40
            | (long)(bytes[3] & 0xff) << 32
            | (long)(bytes[4] & 0xff) << 24
            | (long)(bytes[5] & 0xff) << 16
            | (long)(bytes[6] & 0xff) << 8
            | (long)(bytes[7] & 0xff)
        );
    }

    public static byte encodeBoolean(boolean b) {
        //encodes a boolean to a byte
        return b ? (byte)(1) : (byte)(0);
    }

    public static boolean decodeBoolean(byte value) {
        if (value == 1) return true;
        if (value == 0) return false;
        throw new IllegalArgumentException("Paramter isn't encoded boolean");
    }

    public static byte encode8Booleans(boolean b[]) {
        //encodes 8 bits into a byte

        if (b.length != 8) throw new IllegalArgumentException("Cannot encode more or less than 8 bits into a byte");
        byte toReturn = 0;

        for (boolean value : b) {
            toReturn = (byte)(toReturn << 1);
            

            if (value) {
                toReturn = (byte)(toReturn | 1);
            }
        }

        return toReturn;
    }

    public static boolean[] decode8Booleans(byte b) {
        //decodes one byte into 8 bits

        boolean toReturn[] = new boolean[8];
        
        for (int i = 7; i >= 0; i --) {
            toReturn[i] = ((b >> i & 1) == 1);
        }

        return toReturn;
    }

    public static int pow(int number, int power) throws Exception {
        if (power < 0) throw new IllegalArgumentException("Negative exponent would be a fraction");

        return (int)Math.pow(number, power);
    }

    public static byte max(byte a, byte b) {
        return (byte)(Math.max(a, b));
    }

    public static byte min(byte a, byte b) {
        return (byte)(Math.min(a, b));
    }

    public static byte[] encodeDynamically(Object value) {
        //encodes any primitive type dynamically
        //it can be decoded by it's sister method safely
        //the method uses an extra byte than any given designated encoder method as it also encodes what type the primitive is

        if (value == null) return null;

        switch (value.getClass().getName()) {
            case "java.lang.Byte":
                return new byte[] {(byte)(0), (byte)value};
                
            case "java.lang.Short":
                return concatArrays(new byte[][] {new byte[] {(byte)(1)}, encodeShort((short)value)});

            case "java.lang.Integer":
                return concatArrays(new byte[][] {new byte[] {(byte)(2)}, encodeInt((int)value)});

            case "java.lang.Long":
                return concatArrays(new byte[][] {new byte[] {(byte)(3)}, encodeLong((long)value)});

            case "java.lang.Float":
                return concatArrays(new byte[][] {new byte[] {(byte)(4)}, encodeFloat((float)value)});
                
            case "java.lang.Double":
                return concatArrays(new byte[][] {new byte[] {(byte)(5)}, encodeDouble((double)value)});

            case "java.lang.Character":
                return concatArrays(new byte[][] {new byte[] {(byte)(6)}, encodeShort((short)(char)value)});

            case "java.lang.Boolean":
                return concatArrays(new byte[][] {new byte[] {(byte)(7)}, new byte[] {encodeBoolean((boolean)value)}});
        
            default:
                throw new IllegalArgumentException("Argument is not of type primitive");
        }
    }

    public static Class<?> getTypeOfDynamic(byte[] dynamic) {
        //returns the wrapper class of the primitive that is encoded in the byte array in case it was encoded with encodeDynamically

        if (dynamic == null) return null;

        if (dynamic.length == 0) throw new IllegalArgumentException("Not a dynamically encoded variable");

        switch (dynamic[0]) {
            case 0:
                return Byte.class;

            case 1:
                return Short.class;

            case 2:
                return Integer.class;

            case 3:
                return Long.class;

            case 4:
                return Float.class;

            case 5:
                return Double.class;

            case 6:
                return Character.class;

            case 7: return Boolean.class;
        
            default:
                throw new IllegalArgumentException("Not a dynamically encoded variable");
        }        

    }

    public static <T> T decodeDynamicCastToType(byte[] dynamic, Class<T> type) {
        if (dynamic == null) return null;

        if (type == null) throw new IllegalArgumentException("Cannot cast to null");

        if (dynamic.length == 0) throw new IllegalArgumentException("Not a dynamically encoded variable");

        if (getTypeOfDynamic(dynamic) != type) throw new IllegalArgumentException("Dynamic is not of the expected type");

        switch (type.getName()) {
            case "java.lang.Byte":
                if (dynamic.length < 2) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast(dynamic[1]);

            case "java.lang.Short":
                if (dynamic.length < 3) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast(Encoder.decodeShort(Arrays.copyOfRange(dynamic, 1, 3)));

            case "java.lang.Integer":
                if (dynamic.length < 5) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast(Encoder.decodeInt(Arrays.copyOfRange(dynamic, 1, 5)));

            case "java.lang.Long":
                if (dynamic.length < 9) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast(Encoder.decodeLong(Arrays.copyOfRange(dynamic, 1, 9)));

            case "java.lang.Float":
                if (dynamic.length < 5) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast(Encoder.decodeFloat(Arrays.copyOfRange(dynamic, 1, 5)));

            case "java.lang.Double":
                if (dynamic.length < 9) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast(Encoder.decodeDouble(Arrays.copyOfRange(dynamic, 1, 9)));

            case "java.lang.Character":
                if (dynamic.length < 3) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast((char)Encoder.decodeShort(Arrays.copyOfRange(dynamic, 1, 3)));

            case "java.lang.Boolean":
                if (dynamic.length < 2) throw new IllegalArgumentException("Not a dynamically encoded variable");
                return type.cast(Encoder.decodeBoolean(dynamic[1]));
        
            default:
                throw new IllegalArgumentException("Not a dynamically encoded variable");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T concatArrays(T[] arrays) {

        if (arrays.length == 0) return null;
        if (arrays.length == 1) return arrays[0];
        
        for (T t : arrays) {
            if (t == null) throw new IllegalArgumentException("Element null is not valid");
            if (!t.getClass().isArray()) throw new IllegalArgumentException("Only arrays are accepted");
        }

        Class<?> compType = arrays[0].getClass().arrayType();

        for (T t : arrays) {
            if (!t.getClass().arrayType().equals(compType)) throw new IllegalArgumentException("Arrays aren't of the same type");
        }

        int lengths[] = new int[arrays.length];
        int sumLength = 0;
        for (int i = 0; i < arrays.length; i ++) {
            lengths[i] = Array.getLength(arrays[i]);
            sumLength += lengths[i];
        }

        T toReturn = (T)(Array.newInstance(arrays[0].getClass().componentType(), sumLength));

        int track = 0;
        for (int i = 0; i < lengths.length; i ++) {
            System.arraycopy(arrays[i], 0, toReturn, track, lengths[i]);
            track += lengths[i];
        }

        return toReturn;

    }
    
}
