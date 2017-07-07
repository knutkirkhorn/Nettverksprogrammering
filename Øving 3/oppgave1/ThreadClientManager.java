package oppgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Knut on 12.03.2017.
 */
public class ThreadClientManager extends Thread {

    private Socket socket;

    public ThreadClientManager(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            printWriter.println("Hallo, du har koblet deg til serveren");

            String aLine = bufferedReader.readLine();
            while (aLine != null) {
                System.out.println("Klienten skrev: " + aLine);

                int sumNumbers = -1;
                if (aLine.contains("+")) {
                    sumNumbers = Integer.parseInt(aLine.split("\\+")[0]) + Integer.parseInt(aLine.split("\\+")[1]);
                } else if (aLine.contains("-")) {
                    sumNumbers = Integer.parseInt(aLine.split("-")[0]) - Integer.parseInt(aLine.split("-")[1]);
                }

                printWriter.println("= " + sumNumbers);

                aLine = bufferedReader.readLine();
            }

            bufferedReader.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
