package game.utilities;

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

    public static String encodeShort(short s) {
        //codes a short to a single unicode character string
        return ""+((char)s);
    }

    public static short decodeShort(String code) {
        //decodes 1 unicode character to a short
        
        if (code.length() != 1) throw new IllegalArgumentException("NO");
        
        return (short)code.charAt(0);
    }

    public static short decodeShort(char code) {
        return (short)code;
    }

    public static String encodeInt(int integer) {
        //codes an integer to 2 unicode characters
        StringBuilder hex = new StringBuilder(Integer.toHexString(integer));
        int parts[] = new int[] {Integer.parseInt(hex.substring(0, 4), 16), Integer.parseInt(hex.substring(4, 8), 16)};
        return (char)parts[0]+""+(char)parts[1];
    }

    public static int decodeInt(String code) {
        //decodes 2 unicode characters to integers

        if (code.length() != 2) throw new IllegalArgumentException("NO");
        
        return Integer.parseUnsignedInt(Integer.toHexString((int)code.charAt(0))+Integer.toHexString((int)code.charAt(1)), 16);
    }

    public static String encodeLong(long l) {
        //codes a long to 4 unicode characters

        StringBuilder hex = new StringBuilder(Long.toHexString(l));

        int parts[] = new int[] {Integer.parseInt(hex.substring(0, 4), 16), Integer.parseInt(hex.substring(4, 8), 16), Integer.parseInt(hex.substring(8, 12), 16), Integer.parseInt(hex.substring(12, 16), 16)};
        return (char)parts[0]+""+(char)parts[1]+""+(char)parts[2]+""+(char)parts[3];
    }

    public static long decodeLong(String code) {
        //decodes 4 unicode characters to long

        if (code.length() != 4) throw new IllegalArgumentException("NO");

        return Long.parseUnsignedLong((Integer.toHexString((int)code.charAt(0))+Integer.toHexString((int)code.charAt(1))+Integer.toHexString((int)code.charAt(2))+Integer.toHexString((int)code.charAt(3))), 16);
    }

    public static String encodeDouble(double d) {
        //codes an double to 4 unicode characters
        long l = Double.doubleToRawLongBits(d);
        StringBuilder hex = new StringBuilder(Long.toHexString(l));
        
        int parts[] = new int[] {Integer.parseInt(hex.substring(0, 4), 16), Integer.parseInt(hex.substring(4, 8), 16), Integer.parseInt(hex.substring(8, 12), 16), Integer.parseInt(hex.substring(12, 16), 16)};
        return (char)parts[0]+""+(char)parts[1]+""+(char)parts[2]+""+(char)parts[3];
    }

    public static double decodeDouble(String code) {
        //decodes 4 unicode characters to double

        if (code.length() != 4) throw new IllegalArgumentException("NO");

        return Double.longBitsToDouble(Long.parseUnsignedLong((Integer.toHexString((int)code.charAt(0))+Integer.toHexString((int)code.charAt(1))+Integer.toHexString((int)code.charAt(2))+Integer.toHexString((int)code.charAt(3))), 16));
    }

    public static String encodeBytePair(byte a, byte b) {
        return Encoder.encodeShort((short)((a << 8) + b));
    }

    public static byte[] decodeBytePair(String code) {
        if (code.length() != 1) throw new IllegalArgumentException("NO");

        short combine = Encoder.decodeShort(code);
        byte toReturn[] = new byte[2];
        if ((Short.reverseBytes(combine) & 1) == 1) {toReturn[0] = (byte)((byte)(combine >> 8)+1);}
        else {toReturn[0] = (byte)(combine >> 8);}
        toReturn[1] = (byte)combine;

        return toReturn;
    }

    public static byte[] decodeBytePair(char code) {
        short combine = Encoder.decodeShort(code);
        byte toReturn[] = new byte[2];
        if ((Short.reverseBytes(combine) & 1) == 1) {toReturn[0] = (byte)((byte)(combine >> 8)+1);}
        else {toReturn[0] = (byte)(combine >> 8);}
        toReturn[1] = (byte)combine;

        return toReturn;

    }

    public static int pow(int number, int power) throws Exception {
        if (power < 0) throw new IllegalArgumentException("Negative exponent would be a fraction");
        
        int toReturn = number;
        for (int i = 0; i < power; i ++) {
            toReturn *= number;
        }

        return toReturn;
    }

    public static byte max(byte a, byte b) {
        return (byte)(Math.max(a, b));
    }

    public static byte min(byte a, byte b) {
        return (byte)(Math.min(a, b));
    }
    
}
