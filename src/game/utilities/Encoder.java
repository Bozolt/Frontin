package game.utilities;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import game.exceptions.IllegalFormattingException;

public final class Encoder {
    private Encoder() {throw new UnsupportedOperationException("Utility class shouldn't be constructed");}
    /*
    public static String wrap(String[] contents) throws Exception {
        if (contents.length <= 1) throw new IllegalArgumentException("Too few ("+contents.length+") contents passed to package");

        int depth = 0;

        for (String part : contents) {
            depth = Math.max(depth, getDepth(part));
        }

        String leveledContents[] = contents.clone();

        for (int i = depth; i >= 0; i --) {
            for (int j = 0; j < leveledContents.length; j ++) {
                leveledContents[j].replaceAll("<"+i+">", "<"+(i+1)+">");
            }
        }

        return String.join("<0>", leveledContents);

    }

    public static String[] unwrap(String bundle) throws Exception {

        if (!bundle.contains("<0>")) throw new IllegalFormattingException("Passed argument is malformed or not a package");

        String toReturn[] = bundle.split("<0>");

        for (int i = 0; i < toReturn.length; i ++) {
            int depth = 1;
            while (true) {
                boolean pass = false;
                if (toReturn[i].contains("<"+depth+">")) {pass = true; break;}
                toReturn[i].replaceAll("<"+depth+">", "<"+(depth-1)+">");
                depth ++;
            }
        }
        return toReturn;

    }

    public static int numberOfParts(String bundle) {
        
        int count = 0;
        while (bundle.contains("<0>")) {count ++; bundle.replace("<0>", "");}
        return count;

    }
    

    public static boolean isEncoded(String bundle) {
        return (bundle.contains("<") && bundle.contains(">"));
    }

    public static boolean isProperPackage(String bundle) {
        if (!bundle.contains("<0>")) return false;
        
        for (int i = getDepth(bundle); i > 0; i --) {
            if (!bundle.contains("<"+i+">")) return false;
        }

        return true;
    }
    

    public static int getDepth(String bundle) {
        int depth = 0;
        while (true) {
            boolean pass = false;
            if (bundle.contains("<"+depth+">")) {pass = true; break;}
            if (!pass) break;
            depth ++;
        }
        return depth;
    }
    */

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

    @SuppressWarnings("unchecked")
    public static <T> T concatArrays(T[] arrays) {

        if (arrays.length == 0) return null;
        if (arrays.length == 1) return arrays[0];
        
        for (T t : arrays) {
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
