package Cliend_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientMain {
    private static final String HOST = "localhost";
    private static final int PORT = 1234;
    private static final String EXIT = "CLOSE";

    public static void main(String[] args) {
        	Socket socket;
			try {
				socket = new Socket(HOST, PORT);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				Scanner scanner = new Scanner(System.in);
			
	            ClientProtocol protocol = new ClientProtocol();

	            String username = protocol.registerId();
	            out.println(username);
				
	            Thread readerThread = new Thread(() -> {
	                String msg;
	                try {
	                    while ((msg = in.readLine()) != null) {
	                        protocol.processReply(msg);
	                    }
	                } catch (IOException e) {
	                    System.out.println("Disconnected from server.");
	                }
	            });
	            readerThread.start();
	
	            String input;
	            while (!(input = protocol.prepareRequest(username)).equalsIgnoreCase(EXIT)) {
	                out.println(input);
	            }
			
	            out.println(EXIT);
	            System.out.println("You left the chat.");
	            
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    }
}
