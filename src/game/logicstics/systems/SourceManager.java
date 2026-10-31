package game.logicstics.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.Server;
import game.logicstics.Request;
import game.logicstics.Response;
import game.logicstics.entities.Entity;
import game.utilities.elements.Element;
import game.utilities.elements.NationElement;
import game.utilities.elements.buildingTypes.CityElement;
import game.utilities.elements.geometric.CellElement;
import game.utilities.elements.geometric.PositionElement;

public final class SourceManager {
    
    private SourceManager() {throw new UnsupportedOperationException("Non-instancable class");}

    private static List<FosterRequest> fosters = Collections.synchronizedList(new ArrayList<>());
    private static List<OrphanRequest> orphans = Collections.synchronizedList(new ArrayList<>());

    private static class SourceRequest extends Request {
        public final int parentKey, childKey;

        public SourceRequest(int entityKey, int requestKey, int parentKey, int childKey) {
            super(entityKey, requestKey);
            this.parentKey = parentKey;
            this.childKey = childKey;
        }
    }

    public static class FosterRequest extends SourceRequest {
        public FosterRequest(int entityKey, int requestKey, int parentKey, int childKey) {
            super(entityKey, requestKey, parentKey, childKey);
            fosters.add(this);
        }
    }

    public static class OrphanRequest extends SourceRequest {
        public OrphanRequest(int entityKey, int requestKey, int parentKey, int childKey) {
            super(entityKey, requestKey, parentKey, childKey);
            orphans.add(this);
        }
    }

    private static void postResponse(int entityKey, boolean response) {
        Entity entity = Server.entity.get(entityKey);
        if (entity != null) {
            entity.responses.add(new Response(entityKey, response));
        }
    }

    public static void execute() {
        for (FosterRequest request : fosters) {
            
            Element parent = Server.instance.get(request.parentKey);
            Element child = Server.instance.get(request.childKey);
            if (parent == null || child == null) {
                Server.entity.get(request.entityKey).responses.add(new Response(request.requestKey, false));
            }
            else {
                switch (child) {
                    case CityElement city:
                        if (parent instanceof CellElement) {
                            if (parent.getSourceKey() == Integer.MIN_VALUE) {
                                postResponse(request.entityKey, false);
                            }
                            else {
                                Element grandParent = Server.instance.get(parent.getSourceKey());
                                if (grandParent == null || !(grandParent instanceof NationElement)) {
                                    postResponse(request.entityKey, false);
                                }
                                else {
                                    child.setSourceKey(request.parentKey);
                                    postResponse(request.entityKey, true);
                                }
                            }
                        }
                        else {
                            postResponse(request.entityKey, false);
                        }
                        break;
                
                    default:
                        postResponse(request.entityKey, false);
                        break;
                }
            }
        }
    }

}
