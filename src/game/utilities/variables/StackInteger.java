package game.utilities.variables;

import java.util.concurrent.atomic.AtomicInteger;

public class StackInteger {
    //data structure that increases in value each time you call its current value
    private AtomicInteger value;

    public StackInteger(int value) {
        this.value = new AtomicInteger(value);
    }

    public int value() {;
        return value.getAndIncrement();
    }
}
