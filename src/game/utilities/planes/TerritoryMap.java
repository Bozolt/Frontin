package game.utilities.planes;

import game.utilities.variables.grids.CombiningFlatRowGrid;
import game.utilities.variables.grids.Grid;

public final class TerritoryMap {
    private TerritoryMap() {throw new UnsupportedOperationException("Non-instancable class");}

    public static CombiningFlatRowGrid<Integer> territories = new CombiningFlatRowGrid<>(10000, 10000, Integer.MIN_VALUE);

}
