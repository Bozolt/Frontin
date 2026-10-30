package game.utilities.elements.resourceTypes;

import game.utilities.elements.Element;
import game.utilities.resources.Resource;

//For any element that refers to the Resource cataloge to extend
public class ResourceElement extends Element {
    private int resourceKey;
    private final Class<?> type;

    public <T extends Resource> ResourceElement(int resourceKey, Class<T> type) {
        super();
        setResourceKey(resourceKey);
        if (type == null) throw new IllegalArgumentException("Type of Resource cannot be null");
        this.type = type;
    }

    public int getResourceKey() {return resourceKey;}

    public void setResourceKey(int key) {
        this.resourceKey = key;
    }
}
