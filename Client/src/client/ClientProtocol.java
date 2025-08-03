package client;

import java.io.*;

public class ClientProtocol {

	BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	
	public String registerId() throws IOException {
        System.out.print("Enter your username: ");
        return userInput.readLine();
    }

    public String prepareRequest(String username) throws IOException {
        return userInput.readLine();
    }

    public void processReply(String message) {
        System.out.println(message);
    }
}