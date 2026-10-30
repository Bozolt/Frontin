package game.utilities.variables.grids;

import java.util.ArrayList;
import java.util.List;

public class CombiningAreaGrid <T> extends AreaGrid <T> {
    //A flood grid is a 2d array of set dimensions that instead storing each cells value individually
    //instead stores rectangle areas of where each value appears

    /*
        Like the name suggests the Grid tries to combine adjacent areas to reduce the number of Area nodes to the minimum but this
        takes a lot of checks to be done (at the most width times height of the overwritten area)
    */

    public FloodGrid(int width, int height, T defaultValue) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Cannot create grid with 0 or negative dimension(s)");
        this.width = width;
        this.height = height;
        this.areas.add(new Area<>(0, 0, width, height, defaultValue));
    }

    
}
