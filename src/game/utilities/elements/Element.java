package game.utilities.elements;

import game.Server;
import game.exceptions.IllegalFormattingException;
import game.exceptions.IllegalUnpackingException;
import game.utilities.Encoder;

import java.awt.event.WindowListener;
import java.util.LinkedList;

public class Element {
    
    //run-time identifier
    public final int KEY;

    //collection of child Elements
    private LinkedList<Integer> collection = new LinkedList<>();

    //key of the Element that this Element is the child of
    private int sourceKey = 0;
    
    public Element(int KEY) {

        this.KEY = KEY;

    }

    public boolean containsKey(int searchKey) {
        if (collection.contains(searchKey)) return true;
        int key = Server.instance.get(searchKey).sourceKey;
        while (key != 0) {
            Element ancestor = Server.instance.get(key);
            if (ancestor.KEY == this.KEY) return true;
            if (ancestor.sourceKey == 0) return false;
            key = ancestor.sourceKey;
        }
        return false;
    }

    public void setSource(int sourceKey) {
        if (this.KEY == sourceKey || this.containsKey(sourceKey) || Server.instance.get(sourceKey).containsKey(KEY)) throw new IllegalArgumentException("An Object's ancestor cannot be itself or one of it's collectives nor can it be it's current ancestor's ancestor and so on");
        this.sourceKey = sourceKey;
        Server.instance.get(sourceKey).collection.add(KEY);
    }

    public void addToCollection(int Key) {
        if (collection.contains(Key) || this.KEY == sourceKey || this.containsKey(Key) || Server.instance.get(Key).containsKey(KEY)) throw new IllegalArgumentException("An Object cannot be it's own collective, nor can it's ancestor be it's collective, nor can one of it's already existing collective");
        this.collection.add(Key);
        Server.instance.get(Key).sourceKey = KEY;
    }

    public void removeFromCollection(int Key) {
        if (!collection.contains(Key)) throw new IllegalArgumentException("The provided Key does not belong to an Object that is part of this objects immideate collection");
        collection.remove(Key);
        Server.instance.get(Key).sourceKey = 0;
    }

    public static Element fromCode(String base) throws Exception {
        if (base.length() != 2) throw new IllegalArgumentException("Malformed constructor or not constructor passed");

        return new Element(Encoder.decodeInt(base));
    }
    
    public void patch(String content) {
        throw new UnsupportedOperationException("Unimplemented method");
    }

    public String toCode() {
        return Encoder.encodeInt(this.KEY);
    }

}
