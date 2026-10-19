package game.utilities.packageables.elements.graphics;

import game.utilities.Encoder;
import game.utilities.packageables.Packageable;
import game.utilities.packageables.Point;

public class DynamicGraphic extends StaticGraphic {

    //last timeStamp the cycler cycle the sprite
    protected long previousSpriteUpdate;

    //Which frame of it's sprite the Element is displaying
    protected short imageIndex = 0;

    //Sprite cycling speed relative to the static speed of the sprite
    protected float imageSpeed = 1;

    public DynamicGraphic() {
        super();
    }

    public DynamicGraphic(int KEY, int sourceKey, long assetKey, short layer, boolean visible, Point position, short group,
        float imageXScale, float imageYScale, long previousSpriteUpdate, short imageIndex, float imageSpeed) {
        super(KEY, sourceKey, assetKey, layer, visible, position, group, imageXScale, imageYScale);
        
        setPreviousSpriteUpdate(previousSpriteUpdate);
        setImageIndex(imageIndex);
        setImageSpeed(imageSpeed);
    }

    public long getPreviousSpriteUpdate() {
        return previousSpriteUpdate;
    }

    protected void setPreviousSpriteUpdate(long previousSpriteUpdate) {
        this.previousSpriteUpdate = previousSpriteUpdate;
    }

    public short getImageIndex() {
        return imageIndex;
    }

    protected void setImageIndex(short imageIndex) {
        this.imageIndex = imageIndex;
    }

    public float getImageSpeed() {
        return imageSpeed;
    }

    protected void setImageSpeed(float speed) {
        this.imageSpeed = speed;
    }

    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            super.toBytes(),
            Encoder.encodeLong(previousSpriteUpdate),
            Encoder.encodeShort(imageIndex),
            Encoder.encodeFloat(imageSpeed)
        });
    }

    @Override 
    public int getPackageID() {return Packageable.Package.DynamicGraphic.ordinal();}
 
}
