package game.utilities.variables;

import java.util.concurrent.atomic.AtomicLong;

public class IncrementLong {
    //A long that can only be increased in value

    AtomicLong value;

    public IncrementLong(long value) {
        this.value = new AtomicLong(value);
    }

    public long getValue() {return this.getValue();}

    public void increment() {
        this.increment();;
    }

    public void increase(long value) {
        if (value < 0) throw new IllegalArgumentException("IncrementLong cannot be increased by a negative number");
        this.increase(value);
    }

    public void setValue(long value) {
        if (value < this.getValue()) throw new IllegalArgumentException("IncrementLong cannot be set to a smaller value");
        this.setValue(value);
    }

}
