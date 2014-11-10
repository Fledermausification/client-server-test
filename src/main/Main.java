package main;

import networking.Client;
import networking.Server;
import networking.ServerConstants;
import game.Card;
import game.Deck;
import game.Suit;
import game.Value;

public class Main {
	public static void main(String[] args) {
		/*Deck d = new Deck();
		System.out.println(d.size() + " cards in the deck!");

		int i = 1;
		Card first = d.draw();
		System.out.println(i++ + ": " + first);
		Card c = null;
		while (d.size() > 0) {
			c = d.draw();
			System.out.println(i++ + ": " + c);
		}
		
		//first = new Card(Suit.HEARTS, Value.ACE);
		//c = new Card(Suit.HEARTS, Value.ACE);
		System.out.println("Going to compare '" + first + "' and '" + c + "'");
		int comp = first.compareCard(c);
		if (comp > 0) System.out.println("'" + first + "' > '" + c + "'");
		else if (comp < 0) System.out.println("'" + first + "' < '" + c + "'");
		else System.out.println("'" + first + "' == '" + c + "'");
		//System.out.println("Card: " + comp);*/
		
		
		
		if (args[0].equals("-server")) {
		    Server s = new Server(ServerConstants.PORT_NUMBER, 3);
		    
		}
		else if (args[0].equals("-client")) {
			new Client(ServerConstants.ADDRESS, ServerConstants.PORT_NUMBER, "Client" + (int)(Math.random() * 1000)).start();
		}
	}
}