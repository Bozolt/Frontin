package game.utilities.variables.grids;

public interface Grid <T> {

    /*
        A 2d array of set size and type
    */
    
    public void setArea(int x, int y, int width, int height, T value);

    public T getValue(int x, int y);

}
