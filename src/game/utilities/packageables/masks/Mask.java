package game.utilities.packageables.masks;

import game.utilities.packageables.Point;

public interface Mask {

    public Point[] getPoints();

    public Mask getResized(float xScale, float yScale);

    public static boolean overlaps(Mask a, Mask b, Point A, Point B) {
        if (a == null || b == null) return false;

        Point aPoints[] = a.getPoints();
        Point bPoints[] = b.getPoints();

        switch (aPoints.length) {
            case 0:
                return pointOverlaps(aPoints, bPoints, A, B);

            case 1:
                return circleOverlaps(aPoints, bPoints, A, B);

            case 2:
                return ovalOverlaps(aPoints, bPoints, A, B);
        
            default:
                return polygonOverlaps(aPoints, bPoints, A, B);
        }
    };

    private static boolean pointOverlaps(Point[] a, Point[] b, Point A, Point B) {
        switch (b.length) {
            case 0:
                return (A.x==B.x && A.y==B.y);

            case 1:
                return circleOverlaps(b, a, B, A);

            case 2:
                return ovalOverlaps(b, a, B, A);
        
            default:
                return polygonOverlaps(b, a, B, A);
        }
    }

    private static boolean circleOverlaps(Point[] a, Point[] b, Point A, Point B) {

        switch (b.length) {
            case 0:
                B.x -= A.x;
                B.y -= A.y;
                A.x = 0;
                A.y = 0;
                
                return (B.x*B.x) + (B.y*B.y) <= a[0].x;

            case 1:
                return a[0].x+b[0].x >= Point.distance(A, B);

            case 2:
                return ovalOverlaps(b, a, B, A);
        
            default:
                return polygonOverlaps(b, a, B, A);
        }

    }

    private static boolean ovalOverlaps(Point[] a, Point[] b, Point A, Point B) {
        switch (b.length) {
            case 0:
                //I'll shift both ovals so oval 'a' is centered at (0;0) for shorter expressions
                B.x -= A.x;
                B.y -= A.y;
                A.x = 0;
                A.y = 0;
                
                return (B.x*B.x)/(a[0].x*a[0].x) + (B.y*B.y)/(a[1].y*a[1].y) <= 1;

            case 1:
                throw new UnsupportedOperationException("Unimplemented path");

            case 2:

                //I'll run a simple check to see if the ovals are positions either on the same ordinate or abcissa
                //note that this check may aswell not exist given we are calculating with very high precision numbers
                if (A.x == B.x) return (Math.abs(A.y-B.y) <= a[1].y+b[1].y);
                else if (A.y == B.y) return (Math.abs(A.x-B.x) <= a[0].x+b[0].x);

                //I'll shift both ovals so oval 'a' is centered at (0;0) for shorter expressions
                B.x -= A.x;
                B.y -= A.y;
                A.x = 0;
                A.y = 0;

                //early return for if either ovals' center point is inside or on the line of the other
                {
                    double sqrXDiff = Math.pow(B.x, 2);
                    double sqrYDiff = Math.pow(B.y, 2);
                    
                    if (
                        sqrXDiff/Math.pow(a[0].x, 2) + sqrYDiff/Math.pow(a[1].y, 2) <= 1
                        ||
                        sqrXDiff/Math.pow(b[0].x, 2) + sqrYDiff/Math.pow(b[1].y, 2) <= 1
                    ) return true;
                }
                
                throw new UnsupportedOperationException("Unimplemented path");
        
            default:
                return polygonOverlaps(b, a, B, A);
        }
    }

    private static boolean polygonOverlaps(Point[] a, Point[] b, Point A, Point B) {
        switch (b.length) {
            case 0:
                for (Point focusPoint : a) {
                    Point realFocusPoint = new Point(focusPoint.x+A.x, focusPoint.y+A.y);


                }

                throw new UnsupportedOperationException("Unimplemented path");

            case 1:
                throw new UnsupportedOperationException("Unimplemented path");

            case 2:
                throw new UnsupportedOperationException("Unimplemented path");
        
            default:
                throw new UnsupportedOperationException("Unimplemented path");
        }
    }

    public byte[] toBytes();
}
 