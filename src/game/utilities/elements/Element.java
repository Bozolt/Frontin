package game.utilities.elements;

import game.exceptions.IllegalFormattingException;
import game.exceptions.IllegalUnpackingException;
import game.utilities.Encoder;

import java.awt.event.WindowListener;

public class Element {
    
    public final int KEY;
    
    public Element(int KEY) {

        this.KEY = KEY;

    }

    public static Element fromCode(String base) throws Exception {
        if (base.length() != 2) throw new IllegalArgumentException("Malformed constructor or not constructor passed");

        return new Element(Encoder.decodeInt(base));
    }
    
    public void patch(String content) {
        throw new UnsupportedOperationException("Unimplemented method");
    }

    public String toCode() {
        return Encoder.encodeInt(this.KEY);
    }

}
