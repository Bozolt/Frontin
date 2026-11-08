package game.utilities.variables.grids;

import java.lang.reflect.Array;

public class PlainGrid <T> implements  Grid <T> {

    private final Object[][] grid;

    public PlainGrid(int width, int height, T value) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Dimensions a Grid cannot be 0 or negative");
        if (value == null) throw new IllegalArgumentException("Value cannot be null");
        this.grid = new Object[width][height];
        for (int x = 0; x < grid.length; x ++) {
            for (int y = 0; y < grid[x].length; y ++) {
                this.grid[x][y] = value;
            }
        }
    }

    @Override
    public void setArea(int x, int y, int width, int height, T value) {
        if (x < 0 || y < 0 || x >= this.grid.length || y >= this.grid[0].length) throw new IllegalArgumentException("Starting position out of bounds");
        if (x+(width-1l) >= this.grid.length || y+(height-1l) >= this.grid[0].length) throw new IllegalArgumentException("Ending position out of bounds");
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Area cannot have 0 or negative dimension(s)");
        if (value == null) throw new IllegalArgumentException("Value cannot be null");

        for (int X = x; X < x+width; X ++) {
            for (int Y = y; Y < y+height; Y ++) {
                this.grid[X][Y] = value;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getValue(int x, int y) {
        if (x < 0 || y < 0 || x >= this.grid.length || y >= this.grid[0].length) throw new IllegalArgumentException("Coordinates out of bounds");
        return (T)this.grid[x][y];
    }

    @Override
    public int getWidth() {
        return this.grid.length;
    }

    @Override
    public int getHeight() {
        return this.grid[0].length;
    }

    //as the name suggests it's as plain of a grid as it gets
}
