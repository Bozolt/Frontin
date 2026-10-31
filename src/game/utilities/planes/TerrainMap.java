package game.utilities.planes;

import game.utilities.variables.grids.CombiningFlatRowGrid;

public final class TerrainMap {
    private TerrainMap() {throw new UnsupportedOperationException("Non-instancable class");}

    public enum TerrainType {
        water,
        plain,
        cliff,
        mountain,
        snow
    }

    public static CombiningFlatRowGrid<TerrainType> terrain = new CombiningFlatRowGrid<>(10000, 10000, TerrainType.water);


}
