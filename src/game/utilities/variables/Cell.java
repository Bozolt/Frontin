package game.utilities.variables;

public class Cell {
    public long x, y;

    public Cell(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Cell() {
        this.x = 0;
        this.y = 0;
    }

    public boolean overlaps(Cell other) {
        return (this.x == other.x && this.y == other.y);
    }

    public static double distance(Cell a, Cell b) {
        double dx = a.x-b.x;
        double dy = a.y-b.y;
        return Math.sqrt(dx*dx+dy*dy);
    }
}
