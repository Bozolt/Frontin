package game.utilities.variables;

public class IncrementInteger {
    //An integer that can only be increased in value

    int value;

    public IncrementInteger(int value) {
        this.value = value;
    }

    public int getValue() {return this.value;}

    public void increment() {
        this.value ++;
    }

    public void increase(int value) {
        if (value < 0) throw new IllegalArgumentException("IncrementInteger cannot be increased by a negative number");
        this.value += value;
    }

    public void setValue(int value) {
        if (value < this.value) throw new IllegalArgumentException("IncrementInteger cannot be set to a smaller value");
        this.value = value;
    }

}
