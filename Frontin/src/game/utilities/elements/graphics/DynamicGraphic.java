package game.utilities.elements.graphics;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Map;

import game.Server;
import game.utilities.Encoder;
import game.utilities.elements.Element;
import game.utilities.masks.Mask;
import game.utilities.resources.Sprite;

public class DynamicGraphic extends StaticGraphic {

    //last timeStamp the cycler cycle the sprite
    protected long previousSpriteUpdate;

    //Which frame of it's sprite the Element is displaying
    protected short imageIndex = 0;

    //Sprite cycling speed relative to the static speed of the sprite
    protected float imageSpeed = 1;

    public DynamicGraphic(int KEY) {
        super(KEY);
        


    }

    public long getSpriteKey() {
        return this.spriteKey;
    }

    public void setSpriteKey(long spriteKey, Mask mask) {
        this.spriteKey = spriteKey;
        this.mask = this.fitMask(((Sprite)(Server.resource.get(spriteKey))).getMask());
    }

    private Mask fitMask(Mask mask) {
        return mask.getResized(this.imageXScale, this.imageYScale);
    }
    
    public short getRelativeLayer() {return this.layer;}

    public short getAbsoluteLayer() {
        if (this.sourceKey == 0) return this.layer;
        short sourceLayer = ((StaticGraphic)Server.instance.get(sourceKey)).getAbsoluteLayer();
        int absolute = sourceLayer+this.layer;
        if (absolute > Short.MAX_VALUE) return Short.MAX_VALUE;
        if (absolute < Short.MIN_VALUE) return Short.MIN_VALUE;
        return (short)absolute;
    }

    public void setRelativeLayer(short layer) {
        if (sourceKey == 0 && layer < 0) throw new IllegalArgumentException("Graphic Element's absolute position cannot be negative; Graphic Element without a source cannot have a negative layer reference;");
        this.layer = layer;
    }

    public void setAbsoluteLayer(short layer) {
        if (layer < 0) throw new IllegalArgumentException("Absolute layer cannot be a negative short");
        if (sourceKey == 0) {
            this.layer = layer;
            return;
        }
        short sourceLayer = ((StaticGraphic)Server.instance.get(sourceKey)).getAbsoluteLayer();
        this.layer = (short)(layer-sourceLayer);
    }

    public short getGroup() {return this.group;}

    public void setGroup(short group) {this.group = group;}

    public Mask getMask() {
        return mask;
    }

    public boolean collidesWithAt(Point thisPosition, Object other, Point otherPosition) {
        Collision otherMask = other.getMask();
        
        if (this.mask == null || otherMask == null || this.layer != other.layer) return false;
        return getMask().collidesAt(thisPosition, otherMask, otherPosition);
    }

    public boolean collidesAt(Point thisPosition, Map<String, Object> others) {
        if (this.mask == null) return false;
        Collision trueMask = getMask();
        for (Object other : others.values()) {
            if (other != this && other.layer == this.layer) {
                Collision otherMask = other.getMask();
            
                if (otherMask != null && trueMask.collidesAt(thisPosition, otherMask, other.position)) return true;
            }
        }
        return false;
    }

    public boolean collides(Map<String, Object> others) {
        return collidesAt(this.position, others);
    }

    public LinkedList<Object> collidesAtList(Point thisPosition, Map<String, Object> others) {
        if (this.mask == null) return new LinkedList<>();
        Collision trueMask = getMask();
        LinkedList<Object> objects = new LinkedList<>();
        for (Object other : others.values()) {
            if (other != this && other.layer == this.layer) {
                Collision otherMask = other.getMask();

                if (otherMask != null && trueMask.collidesAt(thisPosition, otherMask, other.position)) objects.add(other);
            }
        }
        return objects;
    }

    public LinkedList<Object> collidesList(Map<String, Object> others) {
        return collidesAtList(this.position, others);
    }

    public boolean getAbsoluteVisibility() {
        if (!visible || sourceKey == 0) return visible;
        int key = sourceKey;
        while (sourceKey != 0) {
            StaticGraphic ancestor = (StaticGraphic)(Server.instance.get(key));
            if (!ancestor.visible) return false;
            key = ancestor.sourceKey;
        }
        return true;
    }

    public void setAbsoluteVisibility(boolean visible) {
        this.visible = visible;
        int key = sourceKey;
        while (key != 0) {
            Element ancestor = Server.instance.get(key);
            if (!(ancestor instanceof StaticGraphic))
            ancestor.setRelativeVisibility(visible);
            key = ancestor.sourceKey;
        }
    }

    public boolean getRelativeVisibility() {
        return visible;
    }

    public void setRelativeVisibility(boolean visible) {
        this.visible = visible;
    }
}
