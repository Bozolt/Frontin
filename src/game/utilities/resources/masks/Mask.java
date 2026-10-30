package game.utilities.resources.masks;

import game.utilities.resources.Resource;
import game.utilities.variables.Point;

public interface Mask {

    public Point[] toPoints();
    
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

    private static boolean pointOverlaps(Point[] a, Point[] b, Point posA, Point posB) {
        switch (b.length) {
            case 0:
                return posA.overlaps(posB);
        
            case 1:
                return circleOverlaps(b, a, posB, posA);

            case 2:
                return ovalOverlaps(b, a, posB, posA);

            default:
                return polygonOverlaps(b, a, posB, posA);
        }
    }

    private static boolean circleOverlaps(Point[] a, Point[] b, Point posA, Point posB){
        switch (b.length) {
            case 0:
                return Point.distance(posA, posB) <= a[0].x;
        
            case 1:
                return Point.distance(posA, posB) <= a[0].x+b[0].x;

            case 2:
                return ovalOverlaps(b, a, posB, posA);

            default:
                return polygonOverlaps(b, a, posB, posA);
        }
    }

    private static boolean ovalOverlaps(Point[] a, Point[] b, Point posA, Point posB){
        switch (b.length) {
            case 0:
                throw new UnsupportedOperationException("Unimplemented branch");
        
            case 1:
                throw new UnsupportedOperationException("Unimplemented branch");

            case 2:
                throw new UnsupportedOperationException("Unimplemented branch");

            default:
                return polygonOverlaps(b, a, posB, posA);
        }
    }

    private static boolean polygonOverlaps(Point[] a, Point[] b, Point posA, Point posB){
        switch (b.length) {
            case 0:
                throw new UnsupportedOperationException("Unimplemented branch");
        
            case 1:
                throw new UnsupportedOperationException("Unimplemented branch");

            case 2:
                throw new UnsupportedOperationException("Unimplemented branch");

            default:
                throw new UnsupportedOperationException("Unimplemented branch");
        }
    }

} 
