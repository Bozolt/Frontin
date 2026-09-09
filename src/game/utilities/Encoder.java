package game.utilities;

import java.util.List;

import game.exceptions.IllegalFormattingException;

public class Encoder {
    /*
    public static String shallow(String original) throws Exception {
        if (original.length() <= 2) throw new IllegalArgumentException("String is too short to be an encoded Package: "+original);
        if (!original.contains("<") || !original.contains(">")) throw new IllegalFormattingException("String isn't an encoded Package or had been encoded improperly: "+original);

        String toReturn = new String(original);
        boolean evaluating = false;
        for (int i = 1; i < original.length(); i ++) {
            if (original.charAt(i-1) == '<') {
                int j = i;
                String value = "";
                evaluating = true;
                while (j < original.length() && original.charAt(j) != '>') {
                    value += original.charAt(j); j ++;
                }
                if (original.charAt(j) == '>') {evaluating = false;}
                
                
            }
        }

        if (!evaluating) throw new IllegalFormattingException("String had been encoded improperly: "+original);



    }
    */
}
