package game.utilities.variables.grids;

import java.rmi.UnexpectedException;
import java.util.ArrayList;

public class CombiningFlatRowGrid <T> implements Grid <T> {
    
    /*
        FlatRowGrids threat the grid as a flat 1d array, each cell ordered left to right

        When two sections of same value touch they get combined automaticly
    */

    protected final int width, height;
    protected final ArrayList<Section<T>> sections = new ArrayList<>();

    public CombiningFlatRowGrid(int width, int height, T defaultValue) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Grid cannot have 0 or negative dimension(s)");
        this.width = width;
        this.height = height;
        this.sections.add(new Section<>(0, defaultValue));
    }

    @Override
    public void setArea(int x, int y, int width, int height, T value) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) throw new IllegalArgumentException("Starting position out of bounds");
        if (x+(width-1l) >= this.width || y+(height-1l) >= this.height) throw new IllegalArgumentException("Ending position out of bounds");
        
        

    }

    @Override
    public T getValue(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) throw new IllegalArgumentException("Position out of bounds");
        int toFind = y*width+x;

        //simple checks for small resolutions
        if (sections.size() == 1) {return sections.getFirst().value;}
        if (sections.size() == 2) {
            if (sections.getLast().i <= toFind) {return sections.getLast().value;}
            return sections.getFirst().value;
        }

        //The Searchs
        int i = sections.size()-1;
        int j = i/2;
        int stepSize = i-j;
        while (true) {
            Section<T> prev = sections.get(i);
            Section<T> curr = sections.get(j);
            if (stepSize == 1) {
                if (prev.i < toFind) {
                    if (curr.i > toFind) {
                        return curr.value;
                    }
                    else if (curr.i < toFind) {
                        //not sure this is one is even possible
                        i ++;
                        j ++;
                        continue;
                    }
                    else {return curr.value;}
                }
                else if (prev.i > toFind) {
                    if (curr.i > toFind) {
                        //once again not sure this one is even reachable
                        i--;
                        j--;
                        continue;
                    }
                    else if (curr.i < toFind) {
                        return curr.value;
                    }
                    else {return curr.value;}
                }
                else {return prev.value;}
            }
            else {
                if (curr.i > toFind) {
                    stepSize /= 2;
                    i = j;
                    j -= stepSize;
                }
                else if (curr.i < toFind) {
                    stepSize /= 2;
                    i = j;
                    j += stepSize;
                }
                else {
                    return curr.value;
                }
            }
        }
    }

    protected static class Section <T> {
        //width and height show how many rows and columns to count starting at the x and y rows and columns
        public int i;
        public T value;

        public Section(int i, T value) {
            if (i < 0) throw new IllegalArgumentException("Start of the section cannot be negative");
            this.i = i;
            this.value = value;
        }
    }
}
