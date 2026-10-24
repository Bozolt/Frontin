package game.utilities.resources;

import java.util.concurrent.ConcurrentHashMap;

import game.utilities.resources.graphical.Image;
import game.utilities.resources.graphical.Sprite;
import game.utilities.resources.masks.OvalMask;
import game.utilities.resources.masks.PolygonMask;
import game.utilities.variables.Point;
import game.utilities.variables.StackInteger;

public class Resource {

    private static final StackInteger keyDistributor = new StackInteger(Integer.MIN_VALUE);
    //String references are for static references in code while at run-time only catalogue references should be used
    private static final ConcurrentHashMap<Integer, Resource> catalogue = new ConcurrentHashMap<>();
    //References can be registered only by private methods and are meant to be not removed at run-time and read in with the addLibrary() method
    private static final ConcurrentHashMap<String, Integer> reference = new ConcurrentHashMap<>();

    //default resources
    private static final Image defaultImage = new Image();
    private static final Sprite defaultSprite = new Sprite();
    private static final OvalMask defaultOvalMask = new OvalMask();
    private static final PolygonMask defaultPolygonMask = new PolygonMask(new Point[] {new Point(-1, -1), new Point(-1, 1), new Point(1, 1), new Point(1, -1)});

    public static Resource getResourceRaw(int key) throws IllegalArgumentException {
        Resource ret = catalogue.get(key);
        if (ret == null) throw new IllegalArgumentException("Missing key or null value");
        return ret;
    }

    public static <T extends Resource> T getDefaultResource(Class<T> type) {
        if (type.equals(Image.class)) {
            return type.cast(defaultImage);
        } else if (type.equals(Sprite.class)) {
            return type.cast(defaultSprite);
        } else if (type.equals(OvalMask.class)) {
            return type.cast(defaultOvalMask);
        } else if (type.equals(PolygonMask.class)) {
            return type.cast(defaultPolygonMask);
        }

        throw new IllegalArgumentException("Unknown resource type or Missing implementation");
    }

    public static <T extends Resource> T getResourceDefaulting(int key, Class<T> type) throws IllegalArgumentException {
        Resource ret = catalogue.get(key);
        if (ret == null || !type.equals(ret.getClass())) {
            return getDefaultResource(type);
        }
        return type.cast(ret);
    }

    public static boolean keyRegistered(int key) {
        return catalogue.containsKey(key);
    }

    public static boolean referenceRegistered(String ref) {
        Integer key = reference.get(ref);
        return key != null ? catalogue.containsKey(key) : false;
    }

    public static int getKey(String ref) throws IllegalArgumentException {
        Integer key = reference.get(ref);
        if (key == null) throw new IllegalArgumentException("Missing reference or key");
        return key;
    }

    public static int addResource(Resource res) {
        int key = keyDistributor.value();
        catalogue.put(key, res);
        return key;
    }

    private static int addResourceWithReference(String ref, Resource res) {
        int key = keyDistributor.value();
        catalogue.put(key, res);
        reference.put(ref, key);
        return key;
    }

    public static void removeResourceWithKey(int key) {
        catalogue.remove(key);
    }

    public static boolean addLibrary(String path) {
        throw new UnsupportedOperationException("Unimplemented method");
    }

}
