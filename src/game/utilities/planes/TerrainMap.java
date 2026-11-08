package game.utilities.planes;

import game.utilities.variables.grids.SuperBooleanFlatRowGrid;

/**
 * TerrainMap
 */
public final class TerrainMap {

    public static SuperBooleanFlatRowGrid grids[] = new SuperBooleanFlatRowGrid[9];

    static {

        for (int i = 0; i < grids.length; i ++) {
            grids[i] = new SuperBooleanFlatRowGrid(10000, 10000, false);
        }

    }
    
    
}