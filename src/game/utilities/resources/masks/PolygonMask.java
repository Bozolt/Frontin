package game.utilities.resources.masks;

import game.utilities.resources.Resource;
import game.utilities.variables.Point;

public class PolygonMask extends Resource implements Mask {
    private final Point points[];

    
    public PolygonMask(Point[] points) {
        this.points = points;
    }

    public Point[] toPoints() {return points;}
    
}
