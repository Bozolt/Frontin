package game.utilities.variables.grids;

import java.util.ArrayList;
import java.util.List;

public class CombiningFloodGrid <T> extends DissectingFloodGrid <T> {
    //A flood grid is a 2d array of set dimensions that instead storing each cells value individually
    //instead stores rectangle areas of where each value appears

    /*
        Like the name suggests the Grid tries to combine adjacent areas to reduce the number of Area nodes to the minimum but this
        takes a lot of checks to be done (at the most width times height of the overwritten area)
    */

    private CombiningFloodGrid(int width, int height, T defaultValue) {
        super(width, height, defaultValue);
        throw new UnsupportedOperationException("Unimplemented class");
    }

    
}
