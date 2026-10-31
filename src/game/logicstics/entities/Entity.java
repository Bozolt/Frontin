package game.logicstics.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.logicstics.Response;
import game.utilities.variables.StackInteger;

public class Entity implements Runnable {
    public List<Response> responses = Collections.synchronizedList(new ArrayList<>());

    protected static StackInteger keyStack = new StackInteger(Integer.MIN_VALUE);

    public final int KEY = keyStack.value();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
