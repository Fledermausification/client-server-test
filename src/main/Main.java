package main;

import game.Card;
import game.Deck;
import server.Server;
import client.ClientConnection;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		Deck d = new Deck();
		System.out.println(d.size() + " cards in the deck!");
		
		int i = 1;
		while (d.size() > 0) {
			Card c = d.draw();
			System.out.println(i++ + ": " + c);
		}
		
		
		
		
		
		if (args[0].equals("-server")) {
		    Server s = new Server(1234, 3);
		    
		}
		else if (args[0].equals("-client")) {
			ClientConnection c = new ClientConnection("localhost", 1234);
		}
	}
}