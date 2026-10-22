package game.utilities.elements;

import game.utilities.variables.IncrementInteger;
import game.utilities.variables.StackInteger;

public class Element {
    protected static StackInteger keyStack = new StackInteger(Integer.MIN_VALUE);

    public final int KEY;

    protected int sourceKey;

    public Element() {
        this.KEY = keyStack.value();
    }

    protected void setSourceKey(int sourceKey) {
        this.sourceKey = sourceKey;
    }    
}
