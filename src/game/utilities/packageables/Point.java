package game.utilities.packageables;

import java.util.Arrays;

import game.utilities.Encoder;

public class Point implements Packageable {
    
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    } 

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
    }

    public double distanceTo(Point other) {
        return Point.distance(this, other);
    }

    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {Encoder.encodeDouble(x), Encoder.encodeDouble(y)});
    }

    @Override 
    public int getPackageID() {return Packageable.Package.Point.ordinal();}
}
