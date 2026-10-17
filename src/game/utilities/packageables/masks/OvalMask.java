package game.utilities.packageables.masks;

import game.utilities.Encoder;
import game.utilities.packageables.Packageable;
import game.utilities.packageables.Point;

public final class OvalMask implements Mask, Packageable {

    private double width, height;
    private boolean circle = false;
    private boolean point = false;
        
    public OvalMask(double width, double height) {
        if (width <= 0) throw new IllegalArgumentException("The width of an OvalMask cannot be zero or less");
        if (height <= 0) throw new IllegalArgumentException("The height of an OvalMask cannot be zero or less");
        this.width = width;
        this.height = height;
    }

    public OvalMask(double radius) {
        if (radius <= 0) throw new IllegalArgumentException("The radius of a CircleMask cannot be zero or less");
        this.width = radius;
        this.height = radius;
        this.circle = true;
    }

    public OvalMask() {
        this.point = true;
    }

    @Override
    public Point[] getPoints() {
        if (point) return new Point[] {};
        if (circle) return new Point[] {new Point(width, 0)};
        return new Point[] {new Point(width, 0), new Point(0, height)};
    }

    @Override
    public Mask getResized(float xScale, float yScale) {
        if (point) return this;
        return new OvalMask(width*xScale, height*yScale);
    }

    @Override
    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            Encoder.encodeDouble(width),
            Encoder.encodeDouble(height),
            new byte[] {Encoder.encode8Booleans(new boolean[] {circle, point, false, false, false, false, false, false})}
        });
    }
}
