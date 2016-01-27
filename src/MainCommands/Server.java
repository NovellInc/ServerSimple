package MainCommands;

/**
 * Created by Omelchuk.Roman on 21.01.2016.
 */

import GameControl.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 4444;
    public static final int timeout = 0; //в миллисекундах, 0 - бесконечность

    public static Game game;

    public static void main(String[] args) throws IOException {

        game = new Game();
        game.initialize();

        ServerSocket server = new ServerSocket(PORT);
        // create server socket
        try {
            int clientCounter = 0;
            System.out.println("Server turned on");
            while(true) {
                Socket client = server.accept();
                try {
                    clientCounter++;
                    new Client(server, client, timeout, clientCounter);
                } catch (IOException e) {
                    System.out.println("Socket closed. Error "+e.toString());
                    client.close();
                }
            }
        }
        catch (IOException e) {
            System.out.printf("Couldn't listen to port %d\n",PORT);
            System.exit(-1);
        }
        finally {
            System.out.println("Server shut down");
            server.close();
        }

    }

}
