package MainCommands;

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

    private Socket fromclient = null;
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
    public ServerThread(ServerSocket server, Socket fromclient, int timeout, int clientCounter) throws IOException {
        this.server = server;
        this.fromclient = fromclient;
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
        try {
            System.out.println("Client "+clientCounter+" connected");
            while (true) {

                in = new BufferedReader(new InputStreamReader(fromclient.getInputStream()));
                out = new PrintWriter(fromclient.getOutputStream(), true);
                String input, output;

                try {
                    //fromclient = server.accept();
                    System.out.printf("Port: %d\nClient number: %d\n", fromclient.getPort(), clientCounter);
                    fromclient.setSoTimeout(timeout);
                    out.print(timeout);
                    System.out.println("Wait for messages");
                    while (true) {
                        input = in.readLine();
                        if (input.contains("close")) {
                            System.out.println("Client disconnected");
                            control.closeSession(out, in);
                            return;
                        }
                        System.out.println(clientCounter + ".Client query :: " + input);
                        output = input + ".." + sdf.format(new Date());
                        out.println("Server response to " + clientCounter + " ::: " + output);
                    }

                } catch (IOException e) {
                    System.out.printf("Request timeout. Client %d disconnected\n", clientCounter);
                    return;
                }
                finally {
                    if (!fromclient.isConnected()) {
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
