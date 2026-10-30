package game.utilities.elements.resourceTypes.graphicTypes;

import game.utilities.elements.Element;
import game.utilities.elements.resourceTypes.ResourceElement;
import game.utilities.resources.Resource;
import game.utilities.resources.graphical.Image;
import game.utilities.resources.graphical.Sprite;

public class StaticGraphicElement extends ResourceElement {
    
    private float imageXScale = 1;
    private float imageYScale = 1;

    private boolean isVisible = true;

    private short depth = 0;

    public StaticGraphicElement() {
        super(Resource.defaultKey, Sprite.class);
    }

    public StaticGraphicElement(int resourceKey) {
        super(resourceKey, Sprite.class);
    }

    public StaticGraphicElement(int resourceKey, float xScale, float yScale, short depth, boolean visible) {
        super(resourceKey, Sprite.class);
        setScale(xScale, yScale);
        setDepth(depth);
        setVisible(visible);
    }

    public float getXScale() {
        return this.imageXScale;
    }

    public void setXScale(float xScale) {
        this.imageXScale = xScale;
    }

    public float getYScale() {
        return this.imageYScale;
    }

    public void setYScale(float yScale) {
        this.imageYScale = yScale;
    }

    public void setScale(float xScale, float yScale) {
        setXScale(xScale);
        setYScale(yScale);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public short getDepth() {
        return this.depth;
    }

    public void setDepth(short depth) {
        this.depth = depth;
    }

}
