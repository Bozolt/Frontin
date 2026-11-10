package game.utilities.resources.masks;

import game.utilities.variables.Point;
import game.utilities.variables.grids.SuperBooleanFlatRowGrid;

public class PixelMask implements Mask {

    private SuperBooleanFlatRowGrid grid;

    public PixelMask(SuperBooleanFlatRowGrid grid) {
        if (grid == null) throw new IllegalArgumentException("Null parameter");
        this.grid = grid;
    }
    
}
