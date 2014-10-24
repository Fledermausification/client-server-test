package client;

import gui.CardFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection extends Thread {
	private CardFrame      frame;
	private PrintWriter    out;
	private BufferedReader in;
	
	public ClientConnection(String address, int port) {
		try {
			Socket connection = new Socket(address, port);
			System.out.println("Connected to the server!");
			
			frame = new CardFrame(this);
			
			out = new PrintWriter(connection.getOutputStream(), true);;
			in  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
				
				
				
				
				try {
					System.out.println("Beep");
					//frame.addMessage("Beep");
					sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("We're done here..");
	}
}