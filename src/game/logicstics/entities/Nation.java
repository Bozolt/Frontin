package game.logicstics.entities;

import game.Server;
import game.logicstics.systems.NationManager;
import game.utilities.variables.Cell;

public class Nation extends Entity {
    private int elementKey;

    public Nation(int elementKey) {
        if (!Server.instance.contains(elementKey)) throw new IllegalArgumentException("Target element doesn't exists");
        this.elementKey = elementKey;
        new NationManager.VirginLandRequest(this.KEY, this.requestKeyStack.value());
    }

    public int getElementKey() {
        return this.elementKey;
    }

    @Override
    public void run() {
        
        

    }
}
