package main;

import server.Server;
import client.Client;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		/*Deck d = new Deck();
		System.out.println(d.size() + " cards in the deck!");
		
		int i = 1;
		while (d.size() > 0) {
			Card c = d.draw();
			System.out.println(i++ + ": " + c);
		}*/
		
		
		
		
		
		if (args[0].equals("-server")) {
		    Server s = new Server(1234, 2);
		    
		}
		else if (args[0].equals("-client")) {
			new Client("localhost", 1234, "Client" + (int)(Math.random() * 1000)).start();
		}
	}
}