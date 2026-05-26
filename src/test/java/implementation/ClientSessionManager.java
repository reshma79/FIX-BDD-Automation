package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSessionManager {

    private static Socket socket;
    private static PrintWriter writer;
    private static BufferedReader reader;

    public void connectSession(String host, int port) {

        try {

            socket = new Socket(host, port);

            writer = new PrintWriter(socket.getOutputStream(), true);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Client FIX Session Connected");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void sendMessage(String message) {

        message = message.replace("|", "\001");

        writer.println(message);

        System.out.println("Client Sent FIX Message:");
        System.out.println(message);
    }

    public static String receiveMessage() {

        try {

            String response = reader.readLine();

            System.out.println("Client Received:");
            System.out.println(response);

            return response;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }
}