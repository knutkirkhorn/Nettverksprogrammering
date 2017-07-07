package oppgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Knut on 07.03.2017.
 */
public class SocketClient {
    public static void main(String[] args) {
        final int PORT_NUMBER = 1337;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv inn maskinnavn til tjener:");
        String serverMachine = scanner.nextLine();

        if (serverMachine.equals("")) {
            serverMachine = "127.0.0.1";
            System.out.println("Kobler til server med ip: " + serverMachine);
        }

        try {
            Socket socket = new Socket(serverMachine, PORT_NUMBER);
            System.out.println("Forbindelse opprettet");

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String firstLine = bufferedReader.readLine();
            System.out.println(firstLine);

            String aLine = scanner.nextLine();
            while (!aLine.equals("")) {
                printWriter.println(aLine);
                String response = bufferedReader.readLine();
                System.out.println("Fra server: " + response);
                aLine = scanner.nextLine();
            }

            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
