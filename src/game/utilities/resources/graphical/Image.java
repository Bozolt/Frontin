package game.utilities.resources.graphical;

import game.utilities.resources.Resource;

import java.awt.image.BufferedImage;

public class Image extends Resource implements Graphical {
    final private BufferedImage image;

    public Image(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {return image;}
}
