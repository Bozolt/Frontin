package game.utilities.types;

public class Float8 {
    //1 sign, 3 exponent, 4 normal

    Byte data = 0b0000000;

    private Float8(byte data) {
        this.data = data;
    }

    public static Float8 parseFloat8(String float8) {

        

    }

    public String toString() {
        return data.floatValue()+"";
    }

}
