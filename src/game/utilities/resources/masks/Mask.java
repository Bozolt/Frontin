package game.utilities.resources.masks;

import game.utilities.resources.Resource;
import game.utilities.variables.Point;

public interface Mask {
    
    public static boolean overlaps(Mask a, Mask b, Point posA, Point posB) {

        Point aPoints[] = a.toPoints();
        Point bPoints[] = b.toPoints();

        switch (aPoints.length) {
            case 0:
                return pointOverlaps(aPoints, bPoints, posA, posB);

            case 1:
                return circleOverlaps(aPoints, bPoints, posA, posB);

            case 2:
                return ovalOverlaps(aPoints, bPoints, posA, posB);
        
            default:
                return polygonOverlaps(aPoints, bPoints, posA, posB);
        }

    }

} 
