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
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			System.out.println("ServerSocket created. inet: " + ss.getInetAddress() + ", local: " + ss.getLocalSocketAddress() + ", port: " + ss.getLocalPort());
			
			clients = new ArrayList <ClientThread> ();
			
			int i = 0;
			
			while (i < maxClients || i > -1) {
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
	        
	        try {
				ss.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		while (true) {
			if (clients.isEmpty()) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Server shutting down...");
	}
	
	public synchronized void broadcastMessage(String message) {
		for (ClientThread ct : clients) {
			ct.writeMessage(message);
		}
	}
	
	private synchronized void removeClient(ClientThread ct) {
		clients.remove(ct);
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
					System.out.println(message);
					if (message != null) {
					    broadcastMessage(message);
					}
				} catch (IOException e) {
					removeClient(this);
					close();
					broadcastMessage("{Client has disconnected}");
					break;
				}
			}
		}
		
		private void close() {
			//Attempt to close all the connections
			try {
				if (out != null) out.close();
			}
			catch (Exception e) {}
			try {
				if (in != null) in.close();
			}
			catch (Exception e) {}
			try {
				if (socket != null) socket.close();
			}
			catch (Exception e) {}
		}
	}
}