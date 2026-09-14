package game.utilities.elements;

import java.awt.Point;

import game.utilities.masks.Mask;

public class Graphic extends Element {

    //graphic and collision layer; relative value
    private byte layer;

    //orders rendering of objects inside the same layer
    private byte depth = 0;

    //Ingame position of the Graphic element; relative value
    private Point position;

    //whetever or not the Graphic element is attempted to be rendered; semi-relative value
    private boolean visible = true;

    //The key relating to the object's sprite; setting it to a sprite that doesn't exists defaults
    private int spriteKey;

    //last timeStamp the cycler cycle the sprite
    private long previousSpriteUpdate;

    //Which frame of it's sprite the Element is displaying
    private short imageIndex = 0;

    private float imageSpeed = 1;

    private byte imageXscale = 1;

    //copy of the sprite's mask, has to be updated everytime the spriteKey is; you can set it to something else then the sprite's mask, no point in it tho
    private Mask mask;

    public Graphic(int KEY) {
        super(KEY);
        


    }
    
    

}
