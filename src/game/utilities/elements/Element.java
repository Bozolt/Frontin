package game.utilities.elements;

import game.utilities.variables.IncrementInteger;

public class Element {
    protected static IncrementInteger keyStack = new IncrementInteger(Integer.MIN_VALUE);

    public final int KEY;

    public int sourceKey;

    public Element() {
        this.KEY = keyStack.getValue();
    }

    public Element(int sourceKey) {
        this.KEY = keyStack.getValue();
        this.sourceKey = sourceKey;
    }
}
