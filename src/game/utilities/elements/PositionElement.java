package game.utilities.elements;

import java.awt.Point;

public class PositionElement extends Element {
    private Point position;

    public PositionElement(Point position) {
        super();
        setPosition(position);
    }

    public Point getPosition() {return this.position;}

    public void setPosition(Point position) {
        if (position == null) throw new IllegalArgumentException("Null object");
        this.position = position;
    }
}
