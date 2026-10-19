package game.utilities.packageables.masks;

import game.utilities.Encoder;
import game.utilities.packageables.Packageable;
import game.utilities.packageables.Point;

/**
 * PolygonMask
 */
public final class PolygonMask implements Mask, Packageable {

    private Point points[];

    public PolygonMask(Point[] points) {
        if (points.length < 3) throw new IllegalArgumentException("Polygon cannot have 2 or less points");
        this.points = points;
    }

    @Override
    public Mask getResized(float xScale, float yScale) {
        
        Point newPoints[] = new Point[points.length];
        for (int i = 0; i < points.length; i ++) {
            newPoints[i] = new Point(points[0].x*xScale, points[0].y*yScale);
        }

        return new PolygonMask(newPoints);

    }

    public int getLength() {
        return points.length;
    }

    @Override
    public byte[] toBytes() {
        byte hold[][] = new byte[points.length+1][8];

        hold[0] = Encoder.encodeInt(getLength());
        
        for (int i = 1; i < hold.length+1; i ++) {
            hold[i] = points[i].toBytes();
        }

        return Encoder.concatArrays(hold);

    }

    @Override 
    public int getPackageID() {return Packageable.Package.PolygonMask.ordinal();}

    @Override
    public Point[] getPoints() {
        return this.points;
    }
}