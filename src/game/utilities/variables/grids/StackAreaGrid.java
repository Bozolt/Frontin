package game.utilities.variables.grids;

import java.util.ArrayList;

public class StackAreaGrid <T> extends HeapAreaGrid <T> {

    /*
        Unlike a HeapAreaGrid on each addition the grid checks and clears Areas that are completely overwritten
        Checking makes this grid more expensive on writes but allows for a set maximum size given it's dimensions and content type
        Due the reduced size of areas it should be as fast or faster on reads aswell
    */

    public StackAreaGrid(int width, int height, T defaultValue) {
        super(width, height, defaultValue);
    }

    @Override
    public void setArea(int x, int y, int width, int height, T value) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) throw new IllegalArgumentException("Starting position out of bounds");

        ArrayList<Integer> overlaps = new ArrayList<>();

        for (int X = x; X < x+width; X ++) {
            for (int Y = y; Y < y+height; Y ++) {
                for (int i = areas.size()-1; i >= 0; i --) {
                    if (areas.get(i).contains(X, Y)) {
                        ((TrackedArea<T>)areas.get(i)).coverage --;
                        break;
                    } 
                }
            }
        }

        areas.add(new TrackedArea<>(x, y, width, height, value));
    }

    protected static class TrackedArea <T> extends Area <T> {
        public int coverage;

        public TrackedArea(int x, int y, int width, int height, T value) {
            super(x, y, width, height, value);
            coverage = Integer.MIN_VALUE + width*height;
        }
    }
    
}
