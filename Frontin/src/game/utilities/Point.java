package game.utilities;

import game.exceptions.IllegalFormattingException;
import game.exceptions.IllegalUnpackingException;

public class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point fromCode(String base) throws Exception {
        if (base.length() != 8) throw new IllegalArgumentException("Malformed constructor or not constructor passed");

        StringBuilder s = new StringBuilder(base);

        return new Point(Encoder.decodeDouble(s.substring(0, 4)), Encoder.decodeDouble(s.substring(4, 8)));
    }

    public String toCode() {
        return Encoder.encodeDouble(this.x)+Encoder.encodeDouble(this.y);
    }
}
