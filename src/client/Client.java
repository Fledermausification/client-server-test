package client;

import gui.CardFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
	private Socket         socket;
	private CardFrame      frame;
	private BufferedReader in;
	private PrintWriter    out;
	private String         username;
	
	public Client(String address, int port, String username) {
		try {
			this.username = username;
			this.username = this.username + "HI";
			socket = new Socket(address, port);
			System.out.println("Connected to the server as " + username);
			
			frame = new CardFrame(this, username);
			
			in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);;
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
	
	public void sendMessage(String message) {
		out.println(message);
	}
	
	public void run() {
		String message;
		try {
			while ((message = in.readLine()) != null) {
				frame.addMessage(message);
			}
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