package GameControl;

import GameControl.Buildings.BuildingType;

/**
 * Created by Omelchuk.Roman on 26.01.2016.
 */
public class RequiredResources {

    private Resources resources;
    private String requiredType;
    private int level;

    public RequiredResources(Resources resources, BuildingType requiredType, int level) {
        this.resources = resources;
        this.requiredType = requiredType.toString();
        this.level = level;
    }


}
