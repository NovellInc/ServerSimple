package GameControl;

import GameControl.Buildings.Building;
import GameControl.Buildings.BuildingType;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 * Created by Omelchuk.Roman on 25.01.2016.
 */
public class Constructing extends Thread {

    private Socket client;
    private PrintWriter response;

    private int level;
    private int cost;
    private int time;
    private Resources resources;
    private Status status;
    private String type;

    private int max_settlers;

    public Constructing(Socket client, PrintWriter response, int level, int cost, BuildingType type, int time, Resources resources, Status status, int max_settlers) {
        this.response = response;
        this.client = client;
        this.level = level;
        this.cost = cost;
        this.type = setType(type);
        this.time = time;
        this.resources = resources;
        this.status = status;
        this.max_settlers = max_settlers;
    }

    public String setType(BuildingType type) {
        return type.toString();
    }

    @Override
    public void run() {
        super.run();
        buildItem(new Building(level, BuildingType.LIVING, cost, time, resources, status, max_settlers));
    }

    public void buildItem(Building building) {
        building.setStatus(Status.BUILDING);

        //response.print("Constructing "+building.getType()+" started...\n");

        String res="";
        for (Map.Entry entry : resources.getResources().entrySet()) {
            res+=entry.getKey()+"-"+entry.getValue()+" ";
        }
        System.out.println("Constructing started | Building type: "+building.getType()+" | Cost: "+building.getCost()+" | Level: "+building.getLevel()+" | Max settlers: "+building.getMax_settlers()+" | Required resources: "+res);
        for(int i=0; i<=time/1000; i++) {
            try {
                sleep(time / 10);
                System.out.println(building.getType()+" building construction process: "+i*100000/time+"%");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        building.setStatus(Status.DONE);
        System.out.println("Constructing of "+building.getType()+" building completed");

        response.println("Constructing "+building.getType()+" complete\n");

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
