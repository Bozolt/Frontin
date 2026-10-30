package game.utilities.variables.grids;

import java.util.ArrayList;

public class HeapAreaGrid <T> implements Grid <T> {
    /*
        An AreaGrid stores values not individually for each cell but instead with Areas that have a set position and size
        Areas on the grid are ordered and a new addition overrides the old values
        
        Heaping in nature because Areas are never removed even if they are completely covered
    */

    protected final int width, height;
    protected final ArrayList<Area<T>> areas = new ArrayList<>();
    protected final T defaultValue;

    public HeapAreaGrid(int width, int height, T defaultValue) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Grid cannot have 0 or negative dimension(s)");
        this.width = width;
        this.height = height;
        this.defaultValue = defaultValue;
    }

    @Override
    public void setArea(int x, int y, int width, int height, T value) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) throw new IllegalArgumentException("Starting position out of bounds");
        if (x+(width-1l) >= this.width || y+(height-1l) >= this.height) throw new IllegalArgumentException("Ending position out of bounds");
        areas.add(new Area<>(x, y, width, height, value));
    }

    @Override
    public T getValue(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) throw new IllegalArgumentException("Position out of bounds");
        for (int i = areas.size()-1; i >= 0; i --) {
            Area<T> current = areas.get(i);
            if (x >= current.getLeftBoundary() && x <= current.getRightBoundary() && y >= current.getTopBoundary() && y <= current.getBottomBoundary()) {
                return current.value;
            }
        }

        return defaultValue;
    }

    protected static class Area <T> {
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

        public boolean contains(int x, int y) {
            return (x >= this.x && x < this.x+width && y >= this.y && y < this.y+height);
        }
    }
}
