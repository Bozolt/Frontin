package game.utilities.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.logicstics.Response;
import game.utilities.variables.IncrementInteger;
import game.utilities.variables.StackInteger;

//masterclass for all Element types to extend
//Generally speaking for any Element public methods should only be called in a thread-safe context
public class Element {

    protected static StackInteger keyStack = new StackInteger(Integer.MIN_VALUE);

    public final int KEY = keyStack.value();

    protected int sourceKey;

    public Element() {
        
    }

    public int getSourceKey() {return sourceKey;}

    public void setSourceKey(int sourceKey) {
        this.sourceKey = sourceKey;
    }
}
