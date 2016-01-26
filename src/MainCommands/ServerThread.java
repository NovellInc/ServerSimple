package MainCommands;

import GameControl.*;
import GameControl.Buildings.BuildingType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Omelchuk.Roman on 22.01.2016.
 */
class ServerThread extends Thread {

    private Socket client = null;
    private ServerSocket server;
    int timeout;
    int clientCounter;

    public ServerThread(ServerSocket server, int timeout, int clientCounter) throws IOException {
        this.server = server;
        this.timeout = timeout;
        this.clientCounter = clientCounter;

        //setDaemon(true);
        setPriority(MAX_PRIORITY);
        start();
    }
    public ServerThread(ServerSocket server, Socket client, int timeout, int clientCounter) throws IOException {
        this.server = server;
        this.client = client;
        this.timeout = timeout;
        this.clientCounter = clientCounter;

        //setDaemon(true);
        setPriority(MAX_PRIORITY);
        start();
    }

    public void run() {
        BufferedReader in;
        PrintWriter out;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        ConnectionControl control = new ConnectionControl();

        Resources resources = null;
        Game game = new Game(10000, resources, 0);
        try {
            System.out.println("Client "+clientCounter+" connected");
            while (true) {

                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                String input, output;

                try {
                    System.out.printf("Port: %d\nClient number: %d\n", client.getPort(), clientCounter);
                    client.setSoTimeout(timeout);
                    out.print(timeout);
                    System.out.println("Wait for messages");
                    while (true) {
                        input = in.readLine();
                        System.out.println(clientCounter + ".Client query :: " + input);
                        output = input + ".." + sdf.format(new Date());
                        //out.println("Server response to " + clientCounter + " ::: " + output);
                        switch (input.toLowerCase()) {
                            case "close": {
                                System.out.println("Client disconnected");
                                control.closeSession(out, in);
                                return;
                            }
                            case "house": {
                                resources = new Resources();
                                //out.print("Building...");
                                resources.addResource(GameControl.ResourcesList.MEET.toString(), 10);
                                resources.addResource(ResourcesList.WATER.toString(), 10);
                                new Constructing(client, out, clientCounter, 1000, BuildingType.LIVING, 5000, resources, Status.BUILDING, 100).start();
                                //out.println("Complete");
                                break;
                            }
                            case "civil": {
                                resources = new Resources();
                                resources.addResource(ResourcesList.ELECTRICITY.toString(), 50);
                                resources.addResource(ResourcesList.WOOD.toString(), 10);
                                new Constructing(client, out, clientCounter, 1000, BuildingType.CIVIL, 8000, resources, Status.BUILDING, 100).start();
                                break;
                            }
                            case "enterprise": {
                                resources = new Resources();
                                resources.addResource(ResourcesList.COAL.toString(), 100);
                                resources.addResource(ResourcesList.ELECTRICITY.toString(), 50);
                                resources.addResource(ResourcesList.IRON.toString(), 150);
                                new Constructing(client, out, clientCounter, 1000, BuildingType.ENTERPRISE, 10000, resources, Status.BUILDING, 100).start();
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
                        System.out.printf("Client %d disconnected\n",clientCounter);
                        control.closeSession(out, in);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
