package game.utilities.variables;

public class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public boolean overlaps(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    public static double distance(Point a, Point b) {
        double dx = a.x-b.x;
        double dy = a.y-b.y;
        return Math.sqrt(dx*dx+dy*dy);
    }
}
