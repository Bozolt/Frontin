package game.logicstics.systems;

import game.logicstics.Request;
import game.utilities.variables.Cell;

public class NationManager {
    private NationManager() {throw new UnsupportedOperationException("Non-instancable class");}

    private class LandRequest extends Request {
        public LandRequest(int entityKey, int requestKey) {
            super(entityKey, requestKey);
        }
    }

    public class SpawnLandRequest extends LandRequest {
        public final Cell requested;
        public VirginLandRequest(Cell cell, int entityKey, int requestKey) {
            super(entityKey, requestKey);
            this.requested = cell;

        }
    }

}
