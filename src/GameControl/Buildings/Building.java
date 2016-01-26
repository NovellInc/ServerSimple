package GameControl.Buildings;

import GameControl.Resources;
import GameControl.Status;

/**
 * Created by Omelchuk.Roman on 25.01.2016.
 */
public class Building {

    private int level;
    private String type;

    private int cost;
    private int time;
    private Resources resources;
    private String status;

    private int max_settlers;
    //private ArrayList<ResourcesList> availableResources;

    public Building(int level, BuildingType type, int cost, int time, Resources resources, Status status, int max_settlers/*, ArrayList<ResourcesList> availableResources*/) {
        this.level = level;
        this.type = setType(type);
        this.cost = cost;
        this.time = time;
        this.resources = resources;
        this.status = setStatus(status);
        this.max_settlers = max_settlers;
        //this.availableResources = availableResources;
    }

    public int getLevel() {
        return level;
    }

    public int getCost() {
        return cost;
    }

    public String setType(BuildingType type) {
        return this.type = type.toString();
    }

    public String getType() {
        return type;
    }

    public int getMax_settlers() {
        return max_settlers;
    }

    public Resources getResources() {
        return resources;
    }

    public String setStatus(Status status) {
        return this.status = status.toString();
    }
}
