package game.utilities.variables;

public class StackInteger {
    //data structure that increases in value each time you call its current value
    private int value;

    public StackInteger(int value) {
        this.value = value;
    }

    public int value() {
        value ++;
        return value-1;
    }
}
