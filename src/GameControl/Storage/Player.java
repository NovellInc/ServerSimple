package GameControl.Storage;

import GameControl.Buildings.Building;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Omelchuk.Roman on 27.01.2016.
 */
public class Player {

    private int level;
    private int budget; //in-game money
    private int money = 0; //real money, default=0
    private String nickName;
    private BufferedImage avatar;
    private int playerNumber;

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private int buildingAmount;

    public Player(int level, int budget, int money, String nickName, BufferedImage avatar, int playerNumber, ArrayList<Building> building, int buildingAmount) {
        this.level = level;
        this.budget = budget;
        this.money = money;
        this.nickName = nickName;
        this.avatar = avatar;
        this.playerNumber = playerNumber;
        this.buildings = building;
        this.buildingAmount = buildingAmount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget -= budget;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void addBuilding(Building building) {
        buildingAmount++;
        buildings.add(building);
    }

    public int getBuildingAmount() {
        return buildingAmount;
    }

    public void setBuildingAmount(int buildingAmount) {
        this.buildingAmount = buildingAmount;
    }
}
