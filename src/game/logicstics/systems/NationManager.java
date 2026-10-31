package game.logicstics.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import game.Server;
import game.logicstics.Request;
import game.logicstics.Response;
import game.logicstics.entities.Entity;
import game.logicstics.entities.Nation;
import game.logicstics.systems.NationManager.SpawnLandRequest;
import game.logicstics.systems.SourceManager.FosterRequest;
import game.utilities.planes.TerrainMap;
import game.utilities.planes.TerritoryMap;
import game.utilities.variables.Cell;

public class NationManager {
    private NationManager() {throw new UnsupportedOperationException("Non-instancable class");}

    private static List<SpawnLandRequest> spawns = Collections.synchronizedList(new ArrayList<>());
    private static List<VirginLandRequest> virgins = Collections.synchronizedList(new ArrayList<>());

    private static class LandRequest extends Request {
        public LandRequest(int entityKey, int requestKey) {
            super(entityKey, requestKey);
        }
    }

    public static class SpawnLandRequest extends LandRequest {
        public final Cell requested;
        public SpawnLandRequest(Cell cell, int entityKey, int requestKey) {
            super(entityKey, requestKey);
            this.requested = cell;
            spawns.add(this);
        }
    }

    public static class VirginLandRequest extends LandRequest {
        public VirginLandRequest(int entityKey, int requestKey) {
            super(entityKey, requestKey);
            virgins.add(this);
        }
    }

    private static void postResponse(int entityKey, boolean response) {
        Entity entity = Server.entity.get(entityKey);
        if (entity != null) {
            entity.responses.add(new Response(entityKey, response));
        }
    }

    public static void execute() {

        for (SpawnLandRequest request : spawns) {
            int castX = (int)request.requested.x, castY = (int)request.requested.y;
            if (TerritoryMap.territories.getValue(castX, castY) == Integer.MIN_VALUE) {
                if (TerrainMap.terrain.getValue(castX, castY) != TerrainMap.TerrainType.water) {
                    Nation nation = (Nation)(Server.entity.get(request.entityKey));
                    if (nation == null) {
                        postResponse(request.entityKey, false);
                    }
                    else {
                        TerritoryMap.territories.setArea(castX, castY, 1, 1, nation.getElementKey());
                        postResponse(request.entityKey, true);
                    }
                }
            }
        }

        for (VirginLandRequest request : virgins) {
            Random r = new Random();
            Nation nation = (Nation)(Server.entity.get(request.entityKey));
            if (nation == null) {
                postResponse(request.entityKey, false);
            }
            else {
                while (true) {
                    int x = r.nextInt(TerrainMap.terrain.getWidth());
                    int y = r.nextInt(TerrainMap.terrain.getHeight());

                    if (TerrainMap.terrain.getValue(x, y) == TerrainMap.TerrainType.water) {
                        if (TerritoryMap.territories.getValue(x, y) == Integer.MIN_VALUE) {
                            TerritoryMap.territories.setArea(x, y, 1, 1, nation.getElementKey());
                            postResponse(request.entityKey, true);
                        }
                    }
                }
            }
        }

    }

}
