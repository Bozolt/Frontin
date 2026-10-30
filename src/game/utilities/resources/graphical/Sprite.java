package game.utilities.resources.graphical;

import game.utilities.resources.Resource;
import game.utilities.resources.masks.Mask;

public class Sprite extends Resource implements Graphical {
    final private long imageKeys[];

    public Sprite(long[] imageKeys) {
        this.imageKeys = imageKeys;
    }

    public long[] getImageKeys() {return this.imageKeys;}

    public long getImage(int imageIndex) {
        if (imageIndex >= imageKeys.length) throw new IllegalArgumentException("ImageIndex array index out of bounds");
        return imageKeys[imageIndex];
    }

    public int getSize() {return imageKeys.length;}
}
