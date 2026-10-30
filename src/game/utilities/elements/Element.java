package game.utilities.elements;

import game.utilities.variables.IncrementInteger;
import game.utilities.variables.StackInteger;

//masterclass for all Element types to extend
//Generally speaking for any Element public methods should only be called in a thread-safe context
public class Element {
    protected static StackInteger keyStack = new StackInteger(Integer.MIN_VALUE);

    public final int KEY;

    protected int sourceKey;

    public Element() {
        this.KEY = keyStack.value();
    }

    public int getSourceKey() {return sourceKey;}

    public void setSourceKey(int sourceKey) {
        this.sourceKey = sourceKey;
    }
}
