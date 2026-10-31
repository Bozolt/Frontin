package game.utilities.elements.buildingTypes;

import game.utilities.elements.Element;

public class CityElement extends Element {
    
    protected int level;
    protected int taken;

    public CityElement() {
        this.level = 1;
        this.taken = 0;
    }

    public CityElement(int level) {
        setLevel(level);
        this.taken = 0;
    }

    public int getLevel() {return this.level;}

    public void setLevel(int level) {
        if (level <= 0) throw new IllegalArgumentException("Level cannot be 0 or negative");
        this.level = level;
    }

    public int getTaken() {return this.taken;}

    public int getFree() {return level-taken;}

    public void setTaken(int taken) {
        if (taken < 0) throw new IllegalArgumentException("Cannot have negative number of roads");
        this.taken = taken;
    }

}
