package game.utilities.resources.masks;

import game.utilities.resources.Resource;
import game.utilities.variables.Point;

public class OvalMask extends Resource implements Mask {
    final private double width, height;
    final private boolean isCircle;

    public OvalMask(double radius) {
        if (radius < 1) throw new IllegalArgumentException("A isCircle cannot have a negative radius");
        this.width = radius;
        this.height = radius;
        this.isCircle = true;
    }

    public OvalMask(double width, double height) {
        if (width < 1 || height < 1) throw new IllegalArgumentException("An oval cannot have negative width or height");
        if (width == height) {
            this.width = width;
            this.height = width;
            this.isCircle = true;
            return;
        }
        this.width = width;
        this.height = height;
        this.isCircle = false;
    }
    
    public double getWidth() {return width;}
    public double getHeight() {return height;}

    public boolean isCircle() {
        return isCircle;
    }

}
