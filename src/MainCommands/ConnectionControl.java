package MainCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Omelchuk.Roman on 22.01.2016.
 */
class ConnectionControl {

    public void closeSession(PrintWriter out, BufferedReader in) throws IOException {
            out.close();
            in.close();
            }

    public void closeConnection(Socket fromclient, ServerSocket server) throws IOException{
            fromclient.close();
            server.close();
            }
}
