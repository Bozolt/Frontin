package game.utilities.packageables.elements.graphics;

import game.Server;
import game.utilities.Encoder;
import game.utilities.packageables.Package;
import game.utilities.packageables.Packageable;
import game.utilities.packageables.Point;
import game.utilities.packageables.elements.Element;
import game.utilities.packageables.masks.Mask;
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
    

    protected short imageXScale = Float.floatToFloat16(1f);
    protected short imageYScale = Float.floatToFloat16(1f);

    //masks belong to sprites but each Element stores a static scaled version of it's Sprite's Mask
    //Element Masks can be null in which case the Element will return false on all collision checks related to it
    protected Mask mask = null;

    public StaticGraphic() {
        super();
    }

    public StaticGraphic(long assetKey) {
        super();
        setAssetKey(assetKey);
    }

    public StaticGraphic(long assetKey, float imageXScale, float imageYScale) {
        super();

    }

    protected StaticGraphic(int KEY, int sourceKey, long assetKey, short layer, boolean visible, Point position, short group,
        float imageXScale, float imageYScale) {
        super(KEY, sourceKey, assetKey);
        setRelativeLayer(layer);
        setRelativeVisibility(visible);
        setRelativePosition(position);
        setGroup(group);
        setScale(imageXScale, imageYScale);
    }

    protected void setAssetKey(long assetKey) {
        this.assetKey = assetKey;
        this.mask = this.fitMask(((Sprite)(Server.resource.get(assetKey))).getMask());
    }

    protected Mask fitMask(Mask mask) {
        return mask.getResized(getXScale(), getYScale());
    }
    
    public short getRelativeLayer() {return this.layer;}

    public short getAbsoluteLayer() {
        if (this.getSourceKey() == 0) return this.layer;
        short sourceLayer = ((StaticGraphic)Server.instance.get(getSourceKey())).getAbsoluteLayer();
        int absolute = sourceLayer+this.layer;
        if (absolute > Short.MAX_VALUE) return Short.MAX_VALUE;
        if (absolute < Short.MIN_VALUE) return Short.MIN_VALUE;
        return (short)absolute;
    }

    protected void setRelativeLayer(short layer) {
        if (getSourceKey() == 0 && layer < 0) throw new IllegalArgumentException("Graphic Element's absolute position cannot be negative; Graphic Element without a source cannot have a negative layer reference;");
        this.layer = layer;
    }

    protected void setAbsoluteLayer(short layer) {
        if (layer < 0) throw new IllegalArgumentException("Absolute layer cannot be a negative short");
        if (getSourceKey() == 0) {
            this.layer = layer;
            return;
        }
        short sourceLayer = ((StaticGraphic)Server.instance.get(getSourceKey())).getAbsoluteLayer();
        this.layer = (short)(layer-sourceLayer);
    }

    public short getGroup() {return this.group;}

    protected void setGroup(short group) {this.group = group;}

    public Mask getMask() {
        return mask;
    }

    public float getXScale() {
        return Float.float16ToFloat(this.imageXScale);
    }

    public float getYScale() {
        return Float.float16ToFloat(this.imageYScale);
    }

    protected void setXScale(float xScale) {
        this.mask = mask.getResized(xScale/getXScale(), 1);
        this.imageXScale = Float.floatToFloat16(xScale);
    }

    protected void setYScale(float yScale) {
        this.mask = mask.getResized(1, yScale/getYScale());
        this.imageYScale = Float.floatToFloat16(yScale);
    }

    protected void setScale(float xScale, float yScale) {
        this.mask = mask.getResized(xScale/getXScale(), yScale/getYScale());
        this.imageXScale = Float.floatToFloat16(xScale);
        this.imageYScale = Float.floatToFloat16(yScale);
    }

    

    public boolean overlapsWithAt(Point thisPosition, int otherKey) {
        Element ancestor = Server.instance.get(otherKey);
        if (!(ancestor instanceof StaticGraphic)) return false;
        StaticGraphic graphicAncestor = ((StaticGraphic)ancestor);
        return Mask.overlaps(getMask(), graphicAncestor.getMask(), thisPosition, graphicAncestor.getAbsolutePosition());
    }

    public boolean overlapsAt(Point thisPosition) {
        for (Element other : Server.instance.values()) {
            if (other instanceof StaticGraphic && Mask.overlaps(getMask(), ((StaticGraphic)other).getMask(), thisPosition, this.getAbsolutePosition())) {
                return true;
            }
        }
        return false;
    }

    public boolean overlaps() {
        for (Element other : Server.instance.values()) {
            if (other instanceof StaticGraphic && Mask.overlaps(getMask(), ((StaticGraphic)other).getMask(), this.getAbsolutePosition(), this.getAbsolutePosition())) {
                return true;
            }
        }
        return false;
    }

    public boolean getAbsoluteVisibility() {
        if (!visible || getSourceKey() == 0) return visible;
        int key = getSourceKey();
        while (key != 0) {
            Element ancestor = Server.instance.get(key);
            if (ancestor instanceof StaticGraphic) {
                if (!((StaticGraphic)ancestor).getRelativeVisibility()) {
                    return false;
                }
            }
            key = ancestor.getSourceKey();
        }
        return true;
    }

    public boolean getRelativeVisibility() {
        return visible;
    }

    protected void setRelativeVisibility(boolean visible) {
        this.visible = visible;
    }

    public Point getRelativePosition() {
        return position;
    }

    private void setRelativePosition(Point position) {
        this.position = position;
    }

    public Point getAbsolutePosition() {
        if (this.getSourceKey() == 0) return this.position;
        double x = this.position.x, y = this.position.y;
        int key = getSourceKey();
        while (key != 0) {
            Element ancestor = Server.instance.get(key);
            if (ancestor instanceof StaticGraphic) {
                StaticGraphic graphicAncestor = ((StaticGraphic)ancestor);
                x += graphicAncestor.getRelativePosition().x;
                y += graphicAncestor.getRelativePosition().y;
            }
            key = ancestor.getSourceKey();
        }
        return new Point(x, y);

    }

    private Point setAbsolutePosition(Point position) {
        Point absoluteAncestorPosition = getAbsolutePosition();
        return new Point(absoluteAncestorPosition.x+this.position.x, absoluteAncestorPosition.y+this.position.y);
    }          

    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            super.toBytes(),
            Encoder.encodeShort(layer),
            position.toBytes(),
            Encoder.encodeShort(group),
            Encoder.encodeShort(imageXScale),
            Encoder.encodeShort(imageYScale),
            (mask != null ? mask.toBytes() : Encoder.encodeInt(0)),
            new byte[] {Encoder.encodeBoolean(visible)}
        });
    }

}