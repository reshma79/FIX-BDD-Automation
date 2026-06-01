package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiverSessionManager {

    private static Socket socket;
    private static BufferedReader reader;

    public void connectSession(String host, int port) {

        try {
        	if(socket == null) {
            socket = new Socket(host, port);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Receiver FIX Session Connected");
        	}
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static String captureOutgoingFixMessage() {

        try {

            String message = reader.readLine();

            System.out.println("Receiver Captured FIX:");
            System.out.println(message);

            return message;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }
    
    public void disconnectSession() {
    	try {

    		if(reader != null) {
    			reader.close();
    			reader = null;
    		}

    		if(socket != null) {
    			socket.close();
    			socket = null;
    		}

    		System.out.println("Receiver Session Disconnected");

    	} catch (Exception e) {

    		e.printStackTrace();
    	}

    }

}