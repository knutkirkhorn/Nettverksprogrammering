package oppgave1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Knut on 07.03.2017.
 */
public class SocketServer {
    public static void main(String[] args) {
        final int PORT_NUMBER = 1337;
        Socket socket = null;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Starter server ...");

            while (true) {
                socket = serverSocket.accept();
                Thread newClientThread = new ThreadClientManager(socket);
                newClientThread.start();
            }



        } catch(IOException ioe) {

        } finally {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
