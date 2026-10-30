package game.utilities.resources.masks;

import game.utilities.resources.Resource;
import game.utilities.variables.Point;

public class OvalMask extends Resource implements Mask {
    final private double width, height;
    final private boolean isCircle, isPoint;

    public OvalMask() {
        this.width = 0;
        this.height = 0;
        this.isCircle = false;
        this.isPoint = true;
    }

    public OvalMask(double radius) {
        if (radius < 0) throw new IllegalArgumentException("A isCircle cannot have a negative radius");
        if (radius == 0) {
            this.width = 0;
            this.height = 0;
            this.isCircle = false;
            this.isPoint = true;
            return;
        }
        this.width = radius;
        this.height = radius;
        this.isCircle = true;
        this.isPoint = false;
    }

    public OvalMask(double width, double height) {
        if (width < 0 || height < 0) throw new IllegalArgumentException("An oval cannot have negative width or height");
        if (width == height) {
            if (width == 0) {
                this.width = 0;
                this.height = 0;
                this.isCircle = false;
                this.isPoint = true;
                return;
            }
            this.width = width;
            this.height = width;
            this.isCircle = true;
            this.isPoint = false;
            return;
        }
        this.width = width;
        this.height = height;
        this.isCircle = false;
        this.isPoint = false;
    }

    public Point[] toPoints() {
        if (isPoint) {
            return new Point[0];
        }
        else if (isCircle) {
            return new Point[] {new Point(width, width)};
        }
        else {return new Point[] {new Point(width, 0), new Point(0, height)};}
    }

    public double getWidth() {return width;}
    public double getHeight() {return height;}

    public boolean isCircle() {
        return isCircle;
    }

    public boolean isPoint() {
        return isPoint;
    }
    

}
