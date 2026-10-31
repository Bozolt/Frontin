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

        if (width == this.width || height == 1) {
            long start = (long)y*this.width+x;
            long end = start+(long)width*height;

            long toFind = start;

            int high;

            //simple checks for small resolutions
            if (sections.size() == 1) {high = 0;}
            else if (sections.size() == 2) {
                if (sections.getLast().i <= toFind) {high = 1;}
                else {high = 0;}
            }
            else {
                int low = 0;
                high = sections.size() - 1;

                while (low <= high) {
                    int mid = low + (high - low) / 2;

                    if (sections.get(mid).i <= toFind) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }

            int left = high;

            toFind = end;

            //simple checks for small resolutions
            if (sections.size() == 1) {high = 0;}
            else if (sections.size() == 2) {
                if (sections.getLast().i <= toFind) {high = 1;}
                high = 0;
            }
            else {
                int low = 0;
                high = sections.size() - 1;

                while (low <= high) {
                    int mid = low + (high - low) / 2;

                    if (sections.get(mid).i <= toFind) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }

            int right = high;

            if (right == left) {
                if (right == sections.size()+1) {
                    //incase you are adding to the very end of the list
                    if (end == (long)this.width*this.height) {
                        //incase the new section runs to the end of the grid it simply inserts
                        sections.addLast(new Section<>(start, value));
                    }
                    else {
                        //insert the new section after the previous last and then adds a section of the same type after itself
                        sections.addLast(new Section<>(start, value));
                        sections.addLast(new Section<>(end, sections.get(left).value));
                    }
                }
                else if (left == 0) {
                    //adding to the beginning of the list
                    if (start == 0) {
                        sections.addFirst(new Section<>(0, value));
                        sections.get(1).i = end;
                    }
                    else {
                        sections.add(1, new Section<>(start, value));
                        sections.add(2, new Section<>(end, sections.getFirst().value));
                    }
                }
                else {
                    sections.add(left+1, new Section<>(start, value));
                    sections.get(right+1).i = end;
                }
            }
            else {
                //removes inbetweens
                for (int i = right-left; i > 0; i --) {
                    sections.remove(left+1);
                    right --;
                }

                if (right == sections.size()+1) {
                    //incase you are adding to the very end of the list
                    if (end == (long)this.width*this.height) {
                        //incase the new section runs to the end of the grid it simply inserts
                        sections.addLast(new Section<>(start, value));
                    }
                    else {
                        //insert the new section after the previous last and then adds a section of the same type after itself
                        sections.addLast(new Section<>(start, value));
                        sections.addLast(new Section<>(end, sections.get(left).value));
                    }
                }
                else if (left == 0) {
                    //adding to the beginning of the list
                    if (start == 0) {
                        sections.addFirst(new Section<>(0, value));
                        sections.get(1).i = end;
                    }
                    else {
                        sections.add(1, new Section<>(start, value));
                        sections.add(2, new Section<>(end, sections.getFirst().value));
                    }
                }
                else {
                    sections.add(left+1, new Section<>(start, value));
                    sections.get(right+1).i = end;
                }
            }


        }
        else {

            //takes it apart into Sections and calls this function recursively for each one so they fall into the condition of the if
            //I'll add this after the bug checks
        }

        

    }

    @Override
    public T getValue(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) throw new IllegalArgumentException("Position out of bounds");
        long toFind = (long)y*width+x;

        //simple checks for small resolutions
        if (sections.size() == 1) {return sections.getFirst().value;}
        if (sections.size() == 2) {
            if (sections.getLast().i <= toFind) {return sections.getLast().value;}
            return sections.getFirst().value;
        }

        int low = 0;
        int high = sections.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (sections.get(mid).i <= toFind) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return sections.get(high).value;
    }

    protected static class Section <T> {
        //width and height show how many rows and columns to count starting at the x and y rows and columns
        public long i;
        public T value;

        public Section(long i, T value) {
            if (i < 0) throw new IllegalArgumentException("Start of the section cannot be negative");
            this.i = i;
            this.value = value;
        }
    }
}
