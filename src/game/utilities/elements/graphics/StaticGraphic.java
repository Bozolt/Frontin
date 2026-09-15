package game.utilities.elements.graphics;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Map;

import game.Server;
import game.utilities.Encoder;
import game.utilities.elements.Element;
import game.utilities.masks.Mask;
import game.utilities.resources.Sprite;

public class StaticGraphic extends Element {

    //graphic and collision layer; a child's layer is relative to it's source's but is limited to be beetwen a shorts range
    //Only the positive side of short is used as absolute values and negatives are to show relative position whiel staying in range of the data type (see source 30000 so for absolute to be 0 layer has to be -30000)
    protected short layer = 0;

    protected boolean visible = true;

    //Ingame position of the Graphic element; relative value
    protected Point position;

    //only Graphic Elements in the same group can collide given their mask isn't null; Collision Groups are absolute values
    protected short group = 0;

    //The key relating to the object's sprite; setting it to a sprite that doesn't exists defaults
    protected long assetKey;

    protected short imageXScale = Float.floatToFloat16(1f);
    protected short imageYScale = Float.floatToFloat16(1f);

    //masks belong to sprites but each Element stores a static scaled version of it's Sprite's Mask
    //Element Masks can be null in which case the Element will return false on all collision checks related to it
    protected Mask mask = null;

    public StaticGraphic(int KEY) {
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
