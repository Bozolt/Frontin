package game.utilities.variables;

import java.util.concurrent.atomic.AtomicLong;

public class StackLong {
    //data structure that increases in value each time you call its current value
    private AtomicLong value;

    public StackLong(long value) {
        this.value = new AtomicLong(value);
    }

    public long value() {;
        return value.getAndIncrement();
    }
}
