package GameControl;

import java.util.ArrayList;

/**
 * Created by Omelchuk.Roman on 25.01.2016.
 */
public class Building extends Thread {

    private int level;
    private int cost;
    private int time;
    private ArrayList<RequiredResources> resources;
    private BuildingStatus status;



    @Override
    public void run() {
        super.run();

    }

    public void buildItem() {

    }

    public void stopbuildItem() {

    }

    public void destroyItem(int cost, int time) {

    }

    public int upgrade(int level, int cost, int time) {

        return level++;
    }

    public int downgrade(int level, int cost, int time) {

        return level--;
    }

}
