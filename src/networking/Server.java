package networking;

import game.Deck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			
			while (i < maxClients) {
			    Socket s = ss.accept();
	            
	            ClientThread ct = new ClientThread(s);
	            ct.start();
	            clients.add(ct);
	            
				System.out.println("Client connected " + i++);
			}
			
			System.out.println("All clients connected!");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Deck d = new Deck();
			
			for (ClientThread ct : clients) {
				System.out.println("Drawing cards for " + ct.username);
				ct.writeObject(new ChatObject("", "All players have connected", ChatObjectType.SERVER_MESSAGE));
				for (i = 0; i < 7; i++) {
					GameObject go = new GameObject(d.draw(), GameObjectType.DRAW);
					ct.writeObject(go);
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
		
		System.out.println("Server shutting down...");
	}
	
	public synchronized void broadcast(Object o) {
		for (ClientThread ct : clients) {
			ct.writeObject(o);
		}
	}
	
	private synchronized void removeClient(ClientThread ct) {
		clients.remove(ct);
	}
	
	private class ClientThread extends Thread {
		private Socket             socket;
		private ObjectInputStream  in;
		private ObjectOutputStream out;
		private String             username;
		
		public ClientThread(Socket s) {
			socket   = s;
			username = "?Unknown?";
			
			//Create the input/output stream reader/writers
            try {
            	//If out isn't declared first then in will fail... That's kinda odd...
	            out = new ObjectOutputStream(s.getOutputStream());
	            out.flush();
				in  = new ObjectInputStream(s.getInputStream());
				System.out.println("Client thread connected");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("ClientThread cound not connect properly");
				e.printStackTrace();
				removeClient(this);
				close();
			}
		}
		
		public void writeObject(Object o) {
			try {
				out.writeObject(o);
				out.flush();
			} catch (IOException e) {
				System.out.println("Could not write Object to " + username);
				e.printStackTrace();
			}
		}
		
		public void run() {
			while (true) {
				try {
					//Wait for a message
					Object rec = in.readObject();
					
					if (rec instanceof ChatObject) {
						ChatObject co = (ChatObject)rec;
						if (co.getType().equals(ChatObjectType.CONNECT)) {
							username = co.getUsername();
						}
						broadcast(co);
					}
					else if (rec instanceof GameObject) {
						GameObject go = (GameObject)rec;
						broadcast(go);
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Unknown object received");
					e.printStackTrace();
				} catch (IOException e) {
					removeClient(this);
					close();
					broadcast(new ChatObject(username, " has disconnected", ChatObjectType.DISCONNECT));
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