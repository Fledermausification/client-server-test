package main;

import game.Card;
import game.Deck;

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
	}
}