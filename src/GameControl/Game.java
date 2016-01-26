package GameControl;

/**
 * Created by Omelchuk.Roman on 26.01.2016.
 */
public class Game {

    private int budget;
    private Resources availableResources;
    private int citizens;

    public Game(int budget, Resources availableResources, int citizens) {
        this.budget = budget;
        this.availableResources = availableResources;
        this.citizens = citizens;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Resources getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(Resources availableResources) {
        this.availableResources = availableResources;
    }

    public int getCitizens() {
        return citizens;
    }

    public void setCitizens(int citizens) {
        this.citizens = citizens;
    }

    public void init() {

    }


}
