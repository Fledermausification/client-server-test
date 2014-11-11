package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	/**
	 * Returns a copy of the List
	 * @return
	 */
	public List <Card> getAll() {
		return new ArrayList <Card> (hand);
	}
	
	public int size() {
		return hand.size();
	}
	
	public void sortBySuit() {
		Collections.sort(hand, new Comparator <Card> () {
			@Override
			public int compare(Card c1, Card c2) {
				int res = c1.getSuit().compareToSuit(c2.getSuit());
				if (res == 0) return c1.getValue().compareToValue(c2.getValue());
				return res;
			}
		});
	}
	
	public void sortByValue() {
		Collections.sort(hand, new Comparator <Card> () {
			@Override
			public int compare(Card c1, Card c2) {
				int res = c1.getValue().compareToValue(c2.getValue());
				if (res == 0) return c1.getSuit().compareToSuit(c2.getSuit());
				return res;
			}
		});
	}
}