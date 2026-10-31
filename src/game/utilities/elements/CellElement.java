package game.utilities.elements;

import game.utilities.variables.Cell;
import game.utilities.variables.Point;

public class CellElement {
    private Cell position;

    public CellElement(long x, long y) {
        super();
        setPosition(new Cell(x, y));
    }

    public CellElement(Cell cell) {
        super();
        setPosition(cell);
    }

    public Cell getPosition() {return this.position;}

    public void setPosition(Cell position) {
        if (position == null) throw new IllegalArgumentException("Null object");
        this.position = position;
    }
}
