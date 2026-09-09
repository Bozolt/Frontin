package game.utilities;

import game.exceptions.IllegalFormattingException;
import game.exceptions.IllegalUnpackingException;

public class Element {
    
    private Point position;
    public final String KEY;
    
    public Element(String KEY, Point position) {

        this.KEY = KEY;
        this.position = position;

    }

    public static Element fromString(String base) throws Exception {
        if (base.contains("<2>")) throw new IllegalUnpackingException("Package is the incorrect depth, the initial Package was unpacked in an incorrect order");
        String parts[] = base.split("<1>");
        if (parts.length != 1) throw new IllegalFormattingException("Illegally formatted Element definer");
        //return new Element(parts[0], Point.fromString(parts[1].))
        return null;
    }
    
}
