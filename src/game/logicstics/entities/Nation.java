package game.logicstics.entities;

import game.Server;
import game.logicstics.systems.NationManager;

public class Nation extends Entity {
    private int elementKey;

    public Nation(int elementKey) {
        if (!Server.instance.contains(elementKey)) throw new IllegalArgumentException("Target element doesn't exists");
        this.elementKey = elementKey;
    }

    @Override
    public void run() {
        
        NationManager.

    }
}
