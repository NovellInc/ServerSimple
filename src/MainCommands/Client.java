package MainCommands;

import GameControl.Buildings.BuildingType;
import GameControl.Constructing;
import GameControl.Resources;
import GameControl.ResourcesList;
import GameControl.Storage.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Omelchuk.Roman on 22.01.2016.
 */
class Client extends Thread {

    private Socket client = null;
    private ServerSocket server;
    int timeout;
    int clientCounter;

    public Client(ServerSocket server, Socket client, int timeout, int clientCounter) throws IOException {
        this.server = server;
        this.client = client;
        this.timeout = timeout;
        this.clientCounter = clientCounter;

        //setDaemon(true);
        setPriority(MAX_PRIORITY);
        start();
    }

    public void run() {
        Server.game.addPlayer(new Player(6, 20000, 0, "Player2", null, 2, 0));
        System.out.println("Created new Player\nNickname: "+Server.game.getPlayer(clientCounter - 1).getNickName()+
                                " | Level: "+Server.game.getPlayer(clientCounter - 1).getLevel()+
                                " | Budget: "+Server.game.getPlayer(clientCounter - 1).getBudget()+
                                " | Building amount: "+Server.game.getPlayer(clientCounter - 1).getBuildingAmount());
        BufferedReader in;
        PrintWriter out;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        ConnectionControl control = new ConnectionControl();
        Resources resources;
        try {
            System.out.println("Client "+clientCounter+" connected");
            while (true) {

                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                String input;

                try {
                    System.out.printf("Port: %d\nClient number: %d\n", client.getPort(), clientCounter);
                    client.setSoTimeout(timeout);
                    out.print(timeout);
                    System.out.println("Wait for messages");
                    while (true) {
                        input = in.readLine();
                        System.out.println(clientCounter + ".Client query :: " + input);
                        switch (input.toString().toLowerCase()) {
                            case "close": {
                                System.out.println("Client disconnected");
                                control.closeSession(out, in);
                                return;
                            }
                            case "house":case "11": {
                                resources = new Resources();
                                resources.addResource(GameControl.ResourcesList.MEET.toString(), 10);
                                resources.addResource(ResourcesList.WATER.toString(), 10);
                                Constructing constructing = new Constructing(client, out, clientCounter, 1, 1000, BuildingType.LIVING, 5000, resources, 100);
                                constructing.setName(BuildingType.LIVING.toString());
                                constructing.start();
                                break;
                            }
                            case "civil":case"12": {
                                resources = new Resources();
                                resources.addResource(ResourcesList.ELECTRICITY.toString(), 50);
                                resources.addResource(ResourcesList.WOOD.toString(), 10);
                                Constructing constructing = new Constructing(client, out, clientCounter, 1, 1000, BuildingType.CIVIL, 8000, resources, 100);
                                constructing.setName(BuildingType.CIVIL.toString());
                                constructing.start();
                                break;
                            }
                            case "enterprise":case"13": {
                                resources = new Resources();
                                resources.addResource(ResourcesList.COAL.toString(), 100);
                                resources.addResource(ResourcesList.ELECTRICITY.toString(), 50);
                                resources.addResource(ResourcesList.IRON.toString(), 150);
                                Constructing constructing = new Constructing(client, out, clientCounter, 1, 1000, BuildingType.ENTERPRISE, 10000, resources, 100);
                                constructing.setName(BuildingType.ENTERPRISE.toString());
                                constructing.start();
                                break;
                            }
                            case "stats":case"2": {
                                Player player = Server.game.getPlayer(clientCounter - 1);
                                String output = "";
                                out = new PrintWriter(client.getOutputStream(), true);
                                output += String.format("Nickname: %s | Level: %d | Budget: %d | Building amount: %d", player.getNickName(), player.getLevel(), player.getBudget(), player.getBuildingAmount());
                                for(int index=0; index<=player.getBuildingAmount()-1; index++) {
                                    String r="";
                                    Resources res = player.getBuildings().get(index).getResources();
                                    for (Map.Entry entry : res.getResources().entrySet()) {
                                        r+=entry.getKey()+"-"+entry.getValue()+" ";
                                    }
                                    output += " || "+(index+1)+
                                              ".Building type: " + player.getBuildings().get(index).getType() +
                                              " | Cost: " + player.getBuildings().get(index).getCost() +
                                              " | Level: " + player.getBuildings().get(index).getLevel() +
                                              " | Max settlers: " + player.getBuildings().get(index).getMax_settlers() +
                                              " | Required resources: " + r +
                                              " || Status: "+player.getBuildings().get(index).getStatus()+" !";
                                }
                                out.println(output);
                                break;
                            }
                        }
                    }

                } catch (IOException e) {
                    System.out.printf("Client %d disconnected\n", clientCounter);
                    return;
                }
                finally {
                    if (!client.isConnected()) {
                        //System.out.printf("Client %d disconnected\n",clientCounter);
                        control.closeSession(out, in);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error in Client: " + e.toString());
        }
    }
}
