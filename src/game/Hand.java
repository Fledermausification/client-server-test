package game;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List <Card> hand;
	
	public Hand() {
		hand = new ArrayList <Card> ();
	}
	
	public void addCard(Card c) {
		if (c != null) hand.add(c);
	}
	
	public void removeCard(Card c) {
		hand.remove(c);
	}
	
	public void removeCard(int i) {
		hand.remove(i);
	}
	
	public int size() {
		return hand.size();
	}
	
	public void sortBySuit() {
		
	}
	
	public void sortByValue() {
		
	}
}