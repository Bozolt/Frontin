package game.utilities.variables.grids;

import game.utilities.variables.grids.StackAreaGrid.TrackedArea;

public class DissectingFloodGrid <T> extends HeapAreaGrid <T> {
    
    /*
        FloodGrids are a type of Area grids that allow for no overlap

        Dissecting in this context means when a new Area is created it cuts of the parts of the other Areas that it overlaps with
        slowly dissecting the Areas

        Generally speaking faster on writes than AreaGrids running in O(n) (n as areas.size()) where n at most width*height
    */

    protected DissectingFloodGrid(int width, int height, T defaultValue) {
        super(width, height, defaultValue);
        throw new UnsupportedOperationException("Unimplemented class");
    }

    @Override
    public void setArea(int x, int y, int width, int height, T value) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) throw new IllegalArgumentException("Starting position out of bounds");
        if (x+(width-1l) >= this.width || y+(height-1l) >= this.height) throw new IllegalArgumentException("Ending position out of bounds");
        Area<T> toBeAdded = new TrackedArea<>(x, y, width, height, value);

        for (int i = 0, limit = areas.size(); i < limit; i ++) {
            Area<T> area = areas.get(i);
            int areaLB = area.getLeftBoundary();
            int areaRB = area.getRightBoundary();
            int areaTB = area.getTopBoundary();
            int areaBB = area.getBottomBoundary();

            int thisLB = toBeAdded.getLeftBoundary();
            int thisRB = toBeAdded.getRightBoundary();
            int thisTB = toBeAdded.getTopBoundary();
            int thisBB = toBeAdded.getBottomBoundary();

            boolean rightBound = false;
            if (areaRB < thisLB) {
                continue;
            }
            else if (areaRB < thisRB) {
                rightBound = true;
            }
            boolean leftBound = false;
            if (areaLB > thisRB) {
                continue;
            }
            else if (areaLB > thisLB) {
                leftBound = true;
            }
            boolean topBound = false;
            if (areaTB > thisBB) {
                continue;
            }
            else if (areaTB > thisTB) {
                topBound = true;
            }
            boolean bottomBound = false;
            if (areaBB < thisTB) {
                continue;
            }
            else if (areaBB < thisBB) {
                bottomBound = true;
            }
            
            //may the gods help us
            if (leftBound && rightBound && topBound && bottomBound) {
                areas.remove(i);
                i --;
                limit --;
                continue;
            }
            else if (leftBound && rightBound) {
                if (topBound) {
                    area.y = thisBB;
                    area.height = areaBB-area.y+1;
                    continue;
                }
                else if (bottomBound) {
                    area.height += thisTB-areaBB-1;
                    continue;
                }
                else {
                    area.height += thisTB-areaBB-1;
                    areas.add(new Area<T>(area.x, thisBB+1, area.width, thisBB-areaBB, area.value));
                    continue;
                }
            }
            else if (leftBound) {
                if (topBound) {
                    area.y = thisBB;
                    area.height = areaBB-area.y+1;
                    areas.add(new Area<T>(thisRB+1, thisTB, areaRB-(thisRB+1)+1, areaBB-thisTB+1, area.value));
                    continue;
                }
                //Unfinished from here
                else if (bottomBound) {
                    area.height += thisTB-areaBB-1;
                    continue;
                }
                else {
                    area.height += thisTB-areaBB-1;
                    areas.add(new Area<T>(area.x, thisBB+1, area.width, thisBB-areaBB, area.value));
                    continue;
                }
            }
        }

        areas.add(toBeAdded);
    }

}
