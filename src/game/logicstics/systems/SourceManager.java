package game.logicstics.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.Server;
import game.logicstics.Request;
import game.logicstics.Response;
import game.utilities.elements.Element;

public final class SourceManager {
    
    private SourceManager() {throw new UnsupportedOperationException("Non-instancable class");}

    private static List<FosterRequest> fosters = Collections.synchronizedList(new ArrayList<>());
    private static List<OrphanRequest> orphans = Collections.synchronizedList(new ArrayList<>());

    private class SourceRequest extends Request {
        public final int parentKey, childKey;

        public SourceRequest(int entityKey, int requestKey, int parentKey, int childKey) {
            super(entityKey, requestKey);
            this.parentKey = parentKey;
            this.childKey = childKey;
        }
    }

    public class FosterRequest extends SourceRequest {
        public FosterRequest(int entityKey, int requestKey, int parentKey, int childKey) {
            super(entityKey, requestKey, parentKey, childKey);
        }
    }

    public class OrphanRequest extends SourceRequest {
        public OrphanRequest(int entityKey, int requestKey, int parentKey, int childKey) {
            super(entityKey, requestKey, parentKey, childKey);
        }
    }

    public void execute() {
        for (FosterRequest request : fosters) {
            
            Element fosterSource = Server.instance.get(request.parentKey);
            while (fosterSource != null) {
                
            }

        }
    }

}
