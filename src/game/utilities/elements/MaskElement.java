package game.utilities.elements;

import game.utilities.resources.masks.Mask;
import game.utilities.resources.masks.OvalMask;

public class MaskElement extends Element {
    private Mask mask;
    private short group;

    public MaskElement() {
        super();
        setMask(new OvalMask());
        setGroup((short)(0));
    }

    public MaskElement(Mask mask) {
        super();
        setMask(mask);
        setGroup((short)(0));
    }

    public MaskElement(Mask mask, short group) {
        super();
        setMask(mask);
        setGroup(group);
    }

    public Mask getMask() {
        return this.mask;
    }

    public void setMask(Mask mask) {
        if (mask == null) throw new IllegalArgumentException("Null object");
        this.mask = mask;
    }

    public short getGroup() {return this.group;}

    public void setGroup(short group) {this.group = group;}

}
