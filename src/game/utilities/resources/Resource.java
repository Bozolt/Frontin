package game.utilities.resources;

import java.util.concurrent.ConcurrentHashMap;

import game.utilities.variables.IncrementLong;
import game.utilities.variables.StackLong;

public class Resource {

    private static final StackLong keyDistributor = new StackLong(Long.MIN_VALUE);
    private  static ConcurrentHashMap<Long, Resource> catalogue = new ConcurrentHashMap<>();

    public static Resource getResource(long key) {
        Resource ret = catalogue.get(key);
        if (ret = null) return 
        return ret;
    }

    public static long addResource(Resource res) {
        catalogue.put(keyDistributor.value(), res);
    }

}
