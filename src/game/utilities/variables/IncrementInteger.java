package game.utilities.variables;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementInteger {
    //An integer that can only be increased in value

    AtomicInteger value;

    public IncrementInteger(int value) {
        this.value = new AtomicInteger(value);
    }

    public int getValue() {return this.getValue();}

    public void increment() {
        this.increment();;
    }

    public void increase(int value) {
        if (value < 0) throw new IllegalArgumentException("IncrementInteger cannot be increased by a negative number");
        this.increase(value);
    }

    public void setValue(int value) {
        if (value < this.getValue()) throw new IllegalArgumentException("IncrementInteger cannot be set to a smaller value");
        this.setValue(value);
    }

}
