package client;

import gui.CardFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
	private CardFrame      frame;
	private BufferedReader in;
	private PrintWriter    out;
	
	public Client(String address, int port, String username) {
		try {
			Socket connection = new Socket(address, port);
			System.out.println("Connected to the server!");
			
			frame = new CardFrame(this, username);
			
			in  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			out = new PrintWriter(connection.getOutputStream(), true);;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		System.out.println("Sending message - " + message);
		out.println(message);
	}
	
	public void run() {
		String message;
		try {
			while ((message = in.readLine()) != null) {
				frame.addMessage(message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("We're done here..");
	}
}