package game.utilities;

import game.exceptions.IllegalFormattingException;
import game.exceptions.IllegalUnpackingException;

public class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point fromString(String base) throws Exception {
        if (base.contains("<1>")) throw new IllegalUnpackingException("Package is the incorrect depth, the initial Package was unpacked in an incorrect order");
        String parts[] = base.split("<0>");
        if (parts.length == 2) throw new IllegalFormattingException("Illegally formatted Point definer");
        return new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    }
}
