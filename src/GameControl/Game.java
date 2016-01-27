package GameControl;

import GameControl.Storage.Player;

import java.util.ArrayList;

/**
 * Created by Omelchuk.Roman on 26.01.2016.
 */
public class Game {

    private ArrayList<Player> players;

    public Game() {
        this.players = new ArrayList<Player>();;
    }

    public void initialize() {
//        players.add(new Player(1, 10000, 0, "Player1", null, 1, null, 0));
//        players.add(new Player(6, 20000, 0, "Player2", null, 2, null, 0));
//        players.add(new Player(7, 30000, 0, "Player3", null, 3, null, 0));
    }

    public Player getPlayer(int playerNumber) {
        return players.get(playerNumber);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
