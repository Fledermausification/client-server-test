package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private List <ClientThread> clients;
	
	public Server(int port, int maxClients) {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("ServerSocket created. inet: " + ss.getInetAddress() + ", local: " + ss.getLocalSocketAddress() + ", port: " + ss.getLocalPort());
			
			clients = new ArrayList <ClientThread> ();
			
			int i = 0;
			
			while (i < maxClients) {
			    Socket s = ss.accept();
	            
	            ClientThread ct = new ClientThread(s);
	            ct.start();
	            clients.add(ct);
	            
				System.out.println("Client connected " + i++);
			}
			
			System.out.println("All clients connected!");
			
			/*for (PrintWriter p : out) {
				p.println("SERVER MESSAGE - Let's have a clean figh- I mean game!");
			}*/
			
			
            /*String inputLine, outputLine;
            while ((inputLine = in[0].readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                if (outputLine.equals("Bye."))
                    break;
            }*/
            
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + port + " or listening for a connection");
	        System.out.println(e.getMessage());
		}
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void broadcast(String message) {
		for (ClientThread ct : clients) {
			ct.writeMessage(message);
		}
	}
	
	private class ClientThread extends Thread {
		private Socket         socket;
		private PrintWriter    out;
		private BufferedReader in;
		
		public ClientThread(Socket s) {
			socket = s;
			
			//Create the input/output stream reader/writers
            try {
				in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
	            out = new PrintWriter(s.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void writeMessage(String message) {
			out.println(message);
		}
		
		public void run() {
			while (true) {
				//Wait for a message
				String message;
				try {
					message = in.readLine();
					broadcast(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}