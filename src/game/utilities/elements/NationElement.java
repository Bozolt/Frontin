package game.utilities.elements;

public class NationElement extends Element {
    private long population;
    private long money;
    
    public NationElement() {
        this.population = 0;
        this.money = 0;
    }

    public long getPopulation() {
        return this.population;
    }

    public long getMoney() {
        return this.money;
    }
}
