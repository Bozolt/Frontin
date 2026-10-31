package game.utilities.variables.grids;

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
        if (defaultValue == null) throw new IllegalArgumentException("Value cannot be null");
        this.width = width;
        this.height = height;
        this.sections.add(new Section<>(0, defaultValue));
    }

    @Override
    public void setArea(int x, int y, int width, int height, T value) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) throw new IllegalArgumentException("Starting position out of bounds");
        if (x+(width-1l) >= this.width || y+(height-1l) >= this.height) throw new IllegalArgumentException("Ending position out of bounds");
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Area cannot have 0 or negative dimension(s)");
        if (value == null) throw new IllegalArgumentException("Value cannot be null");

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

            int right = high;

            int index;
            if (right != left) {
                //removes inbetweens
                for (int i = right-left-1; i > 0; i --) {
                    sections.remove(left+1);
                    right --;
                }

                sections.add(left+1, new Section<>(start, value));
                sections.get(left+2).i = end;
                index = left+1;
            }
            else if (right == sections.size()-1) {
                //incase you are adding to the very end of the list
                if (end == (long)this.width*this.height) {
                    //incase the new section runs to the end of the grid it simply inserts
                    sections.addLast(new Section<>(start, value));
                    index = sections.size()-1;
                }
                else {
                    //insert the new section after the previous last and then adds a section of the same type after itself
                    sections.addLast(new Section<>(start, value));
                    sections.addLast(new Section<>(end, sections.get(left).value));
                    index = sections.size()-2;
                }
            }
            else if (left == 0) {
                //adding to the beginning of the list
                if (start == 0) {
                    sections.addFirst(new Section<>(0, value));
                    sections.get(1).i = end;
                    index = 0;
                }
                else {
                    sections.add(1, new Section<>(start, value));
                    sections.add(2, new Section<>(end, sections.getFirst().value));
                    index = 1;
                }
            }
            else {
                sections.add(left+1, new Section<>(start, value));
                sections.add(left+2, new Section<>(end, sections.get(left).value));
                index = left+1;
            }

            //combining logic
            left = index-1;
            right = index+1;
            Section<T> leftSection = left >= 0 ? sections.get(left) : null;
            Section<T> rightSection = right < sections.size() ? sections.get(right) : null;
            Section<T> thisSection = sections.get(index);
            while (leftSection != null) {
                if (leftSection.i == thisSection.i) {
                    sections.remove(left);
                    left --;
                    leftSection = left >= 0 ? sections.get(left) : null;
                    index --;
                    right --;
                }
                else if (leftSection.value.equals(value)) {
                    thisSection.i = leftSection.i;
                    sections.remove(leftSection);
                    left --;
                    leftSection = left >= 0 ? sections.get(left) : null;
                    index --;
                    right --;
                }
                else {break;}
            }
            while (rightSection != null) {
                if (rightSection.i == thisSection.i) {
                    sections.remove(right);
                    rightSection = right < sections.size() ? sections.get(right) : null;
                }
                else if (rightSection.value.equals(value)) {
                    sections.remove(right);
                    rightSection = right < sections.size() ? sections.get(right) : null;
                }
                else {break;}
            }

            //cleans up any sections that run outside the grid
            while (true) {
                if (sections.getLast().i >= (long)width*height) {sections.removeLast();}
                else {break;}
            }

        }
        else {

            //takes it apart into Sections and calls this function recursively for each one so they fall into the condition of the if
            for (int Y = y; Y < y+height; Y ++) {
                setArea(x, Y, width, 1, value);
            }

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
