package game.utilities.elements.resourceTypes.graphicTypes;

import game.utilities.elements.resourceTypes.ResourceElement;
import game.utilities.resources.Resource;
import game.utilities.resources.graphical.Graphical;
import game.utilities.resources.graphical.Image;
import game.utilities.resources.graphical.Sprite;

public class DynamicGraphicElement extends ResourceElement implements Graphical {
    
    private float imageXScale = 1;
    private float imageYScale = 1;

    private boolean isVisible = true;

    private short depth = 0;

    private int imageIndex = Integer.MIN_VALUE;

    private float imageSpeed = 1;

    private long lastUpdate;

    private int spriteSize;

    public DynamicGraphicElement() {
        super(Resource.defaultKey, Sprite.class);
        this.spriteSize = Resource.getResourceDefaulting(KEY, Sprite.class).getSize();
        refreshLastUpdate();
    }

    public DynamicGraphicElement(int resourceKey) {
        super(resourceKey, Sprite.class);
        this.spriteSize = Resource.getResourceDefaulting(KEY, Sprite.class).getSize();
        refreshLastUpdate();
    }

    public DynamicGraphicElement(int resourceKey, float xScale, float yScale, short depth, boolean visible) {
        super(resourceKey, Sprite.class);
        this.spriteSize = Resource.getResourceDefaulting(KEY, Sprite.class).getSize();
        refreshLastUpdate();
        setScale(xScale, yScale);
        setDepth(depth);
        setVisible(visible);
    }

    public DynamicGraphicElement(int resourceKey, float xScale, float yScale, float imageSpeed, short depth, boolean visible) {
        super(resourceKey, Sprite.class);
        this.spriteSize = Resource.getResourceDefaulting(KEY, Sprite.class).getSize();
        refreshLastUpdate();
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
    
    public float getImageSpeed() {return this.imageSpeed;}

    public void setImageSpeed(float imageSpeed) {this.imageSpeed = imageSpeed;}

    public int getImageIndex() {return this.imageIndex;}

    public void setImageIndex(int index) {
        this.imageIndex = index%spriteSize;
    }

    public void incrementImageIndex() {this.imageIndex = (this.imageIndex + 1)%spriteSize;}
    public void DecrementImageIndex() {this.imageIndex = (this.imageIndex - 1)%spriteSize;}

    public void increaseImageIndex(int amount) {this.imageIndex = (this.imageIndex + amount)%spriteSize;}
    public void decreaseImageIndex(int amount) {this.imageIndex = (this.imageIndex - amount)%spriteSize;}

    public long getLastUpdate() {return this.lastUpdate;}

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void refreshLastUpdate() {this.lastUpdate = System.nanoTime();}

    
}
