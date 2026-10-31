package game.utilities.planes;

import game.utilities.variables.grids.CombiningFlatRowGrid;

public final class TerritoryMap {
    private TerritoryMap() {throw new UnsupportedOperationException("Non-instancable class");}

    public static CombiningFlatRowGrid<Integer> terrain = new CombiningFlatRowGrid<>(10000, 10000, 0);

}
