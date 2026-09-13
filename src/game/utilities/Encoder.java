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

    public static String encodeDouble(double d) {
        //codes an double to 4 unicode characters
        long l = Double.doubleToRawLongBits(d);
        StringBuilder hex = new StringBuilder(Long.toHexString(l));
        System.out.println(hex);
        
        int parts[] = new int[] {Integer.parseInt(hex.substring(0, 4), 16), Integer.parseInt(hex.substring(4, 8), 16), Integer.parseInt(hex.substring(8, 12), 16), Integer.parseInt(hex.substring(12, 16), 16)};
        return (char)parts[0]+""+(char)parts[1]+""+(char)parts[2]+""+(char)parts[3];
    }

    public static double decodeDouble(String code) {
        //decodes 4 unicode characters to double

        if (code.length() != 4) throw new IllegalArgumentException("NO");

        return Double.longBitsToDouble(Long.parseUnsignedLong((Integer.toHexString((int)code.charAt(0))+Integer.toHexString((int)code.charAt(1))+Integer.toHexString((int)code.charAt(2))+Integer.toHexString((int)code.charAt(3))), 16));
    }
    
}
