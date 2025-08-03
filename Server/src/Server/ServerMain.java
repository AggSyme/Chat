package Server;

import java.net.*;
import java.io.*;

public class ServerMain {
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {

		ServerSocket serverSocket  = new ServerSocket(PORT);
		
		while (true) {	

			System.out.println("Server is listening to port: " + PORT);
			Socket clientSocket  = serverSocket .accept();
			System.out.println("Received request from " + clientSocket .getInetAddress());

			ServerThread sthread = new ServerThread(clientSocket);
			sthread.start();
		}
	}
}


