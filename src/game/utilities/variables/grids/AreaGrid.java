package game.utilities.variables.grids;

import java.util.ArrayList;

public class AreaGrid <T> implements Grid <T> {
    /*
        An AreaGrid stores values not individually for each cell but instead with Areas that have a set position and size
        Areas on the grid are ordered and a new addition overrides the old values
        
        Areas that had been completely overwritten are removed
    */

    protected final int width, height;
    protected final ArrayList<Area<T>> areas = new ArrayList<>();

    public AreaGrid(int width, int height, T defaultValue) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Grid cannot have 0 or negative dimension(s)");
        this.width = width;
        this.height = height;
        this.areas.add(new Area<>(0, 0, width, height, defaultValue));
    }

    @Override
    public void setArea(int x, int y, int width, int height, T value) {
        if (x < 0 || y < 0 || x >= width || y >= height) throw new IllegalArgumentException("Starting position out of bounds");
        areas.add(new Area<>(x, y, width, height, value));
    }

    @Override
    public T getValue(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) throw new IllegalArgumentException("Position out of bounds");
        for (int i = areas.size()-1; i >= 0; i ++) {
            Area<T> current = areas.get(i);
            if (x >= current.getLeftBoundary() && x <= current.getRightBoundary() && y >= current.getTopBoundary() && y <= current.getBottomBoundary()) {
                return current.value;
            }
        }
    }

    protected class Area <T> {
        //width and height show how many rows and columns to count starting at the x and y rows and columns
        public int x, y, width, height;
        public T value;

        public Area(int x, int y, int width, int height, T value) {
            if (x < 0 || y < 0) throw new IllegalArgumentException("Area cannot start out of bounds");
            if (width <= 0 || height <= 0) throw new IllegalArgumentException("Area cannot have 0 or negative dimensions");
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.value = value;
        }

        public int getLeftBoundary() {return this.x;}
        public int getRightBoundary() {return this.x+this.width-1;}
        public int getTopBoundary() {return this.y;}
        public int getBottomBoundary() {return this.y+this.height-1;}
    }
}
