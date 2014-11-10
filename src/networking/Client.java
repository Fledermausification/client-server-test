package networking;

import gui.CardFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
	private Socket             socket;
	private CardFrame          frame;
	private ObjectInputStream  in;
	private ObjectOutputStream out;
	private String             username;
	
	public Client(String address, int port, String username) {
		try {
			this.username = username;
			this.username = this.username + "HI";
			socket = new Socket(address, port);
			System.out.println("Connected to the server as " + username);
			
			frame = new CardFrame(this, username);
			
			in  = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			
			//Tell the server you've connected!
			writeObject(new ChatObject(username, " has connected", ChatObjectType.CONNECT));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
	}
	
	public void writeObject(Object o) {
		try {
			out.writeObject(o);
			out.flush();
		} catch (IOException e) {
			System.out.println("Could not write Object from " + username);
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			Object rec;
			while ((rec = in.readObject()) != null) {
				if (rec instanceof ChatObject) {
					ChatObject     co   = (ChatObject)rec;
					ChatObjectType type = co.getType();
					
					if (type.equals(ChatObjectType.CONNECT) || type.equals(ChatObjectType.DISCONNECT)) {
						frame.addMessage("- {" + co.getUsername() + co.getMessage() + "}");
					}
					else if (type.equals(ChatObjectType.USER_MESSAGE)) {
						frame.addMessage(co.getUsername() +": " + co.getMessage());
					}
					else if (type.equals(ChatObjectType.SERVER_MESSAGE)) {
						frame.addMessage("- {" + co.getMessage() + "}");
					}
					else if (type.equals(ChatObjectType.ERROR)) {
						frame.addMessage("- {ERROR: " + co.getMessage() + "}");
					}
				}
				else if (rec instanceof GameObject) {
					GameObject     go   = (GameObject)rec;
					GameObjectType type = go.getType();
					//Do something with it
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Unknown object received");
			e.printStackTrace();
		} catch (IOException e) {
			frame.addMessage("{Your connection to the server has been lost}");
			close();
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