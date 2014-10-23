package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List <Card> cards;
	
	public Deck() {
		cards = new ArrayList <Card> ();
		
		//Add the standard cards
		for (Suit s : Suit.values()) {
			if (s != Suit.UNDEFINED) {
			    for (Value v : Value.values()) {
				    if (v != Value.JOKER) {
				    	cards.add(new Card(s, v));
				    }
			    }
			}
		}
		
		//Add 2 jokers
		cards.add(new Card(Suit.UNDEFINED, Value.JOKER));
		cards.add(new Card(Suit.UNDEFINED, Value.JOKER));
		
		//Shuffle the deck
		shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card draw() {
		return cards.remove(0);
	}
	
	public int size() {
		return cards.size();
	}
}