package game.utilities.packageables.masks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import game.utilities.Encoder;
import game.utilities.packageables.Packageable;
import game.utilities.packageables.Point;

/**
 * PolygonMask
 */
public final class PolygonMask implements Mask, Packageable {

    private List<byte[]> patches = Collections.synchronizedList(new LinkedList<byte[]>());

    public void addPatch(byte[] patch) {patches.add(patch);}

    private Point points[];

    public PolygonMask(Point[] points) {
        if (points.length < 3) throw new IllegalArgumentException("Polygon cannot have 2 or less points");
        this.points = points;
    }

    @Override
    public Mask getResized(float xScale, float yScale) {
        
        Point newPoints[] = new Point[points.length];
        for (int i = 0; i < points.length; i ++) {
            newPoints[i] = new Point(points[i].x*xScale, points[i].y*yScale);
        }

        return new PolygonMask(newPoints);

    }

    public int getLength() {
        return points.length;
    }

    @Override
    public byte[] toBytes() {
        byte hold[][] = new byte[points.length][];
        
        for (int i = 0; i < points.length; i ++) {
            hold[i] = points[i].toBytes();
        }

        return Encoder.concatArrays(new byte[][] {
            Encoder.encodeInt(points.length),
            Encoder.concatArrays(hold)
        });

    }

    @Override 
    public int getPackageID() {return Packageable.Package.PolygonMask.ordinal();}

    @Override
    public Point[] getPoints() {
        return this.points;
    }

    @Override
    public boolean executePatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executePatch'");
    }
}