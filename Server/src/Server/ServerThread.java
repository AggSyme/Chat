package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class ServerThread extends Thread
{
	private static final String EXIT = "CLOSE";
	public static ArrayList<ServerThread> clients=new ArrayList<>();
	private Socket dataSocket;
	
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
	private BufferedWriter out;
   	private PrintWriter out_;
   	public String cliendUserName;

   	public ServerThread(Socket socket)
   	{
		dataSocket = socket;
		try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out_ = new PrintWriter(os,true);
			out = new BufferedWriter(new OutputStreamWriter(os));
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
   	}

	public void run()
	{
		
   		String inmsg=null;
		
		try {			
			cliendUserName=in.readLine(); // first message = username
			broadcastMessage("SERVER: " + cliendUserName+" has endered the chat");
			clients.add(this);

			while(!(inmsg= in.readLine()).equals(EXIT)) {
				broadcastMessage(inmsg);
			}
			broadcastMessage("Cliend: " + cliendUserName + " left the chat");
			clients.remove(this);
			dataSocket.close();
			System.out.println("Data socket closed");

		} catch (IOException e)	{		
	 		System.err.println("I/O Error " + e);
		}
	}	
	
	public void broadcastMessage(String string) throws IOException {
		for(ServerThread i: clients) {
			if(!i.getUsername().equals(cliendUserName)) {
				i.out.write(string);
				i.out.newLine();
				i.out.flush();
			}
		}
		
	}
	
	private String getUsername() {
		return this.cliendUserName;
	}
}	
			
		
