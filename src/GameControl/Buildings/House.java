package GameControl.Buildings;

import GameControl.BuildingStatus;
import GameControl.Resources;

import java.util.ArrayList;

/**
 * Created by Omelchuk.Roman on 25.01.2016.
 */
public class House {

    private int level;
    private String type;

    private int cost;
    private int time;
    private ArrayList<RequiredResources> resources;
    private BuildingStatus status;

    private int settlers;
    private ArrayList<Resources> availableResources;
    private enum RequiredResources {}


}
