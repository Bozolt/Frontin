package game.utilities.elements;

import game.exceptions.IllegalFormattingException;
import game.utilities.Encoder;
import game.utilities.Point;

public class Instance extends Element {

    public final int KEY;
    
    public final int ID = 1;

    Point position;

    public Instance(int KEY, Point position) {
        super(KEY);
        this.KEY = KEY;
        this.position = position;
        //TODO Auto-generated constructor stub
    }

    public static Instance fromCode(String base) throws Exception {
        if (base.length() != 10) throw new IllegalArgumentException("Malformed constructor or not constructor passed");

        StringBuilder s = new StringBuilder(base);

        return new Instance(Encoder.decodeInt(s.substring(0, 2)), Point.fromCode(s.substring(2, 10)));
    }
    

    public String toCode() {
       return Encoder.encodeInt(this.KEY)+this.position.toCode();
    }

}
