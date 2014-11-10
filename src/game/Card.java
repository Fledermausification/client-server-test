package game;

import java.io.Serializable;

public class Card implements Serializable {
	private Suit  suit;
	private Value value;
	
	public Card(Suit s, Value v) {
		suit  = s;
		value = v;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Value getValue() {
		return value;
	}
	
	public String toString() {
		if (value.equals(Value.JOKER)) return value.toString();
		return value + " of " + suit;
	}
	
	public int compareSuit(Card c) {
		return suit.compareToSuit(c.suit);
	}
	
	public int compareValue(Card c) {
		return value.compareToValue(c.value);
	}
	
	/**
	 * Joker > ALL (except other Jokers)
	 * Hearts > Diamonds > Clubs > Spades
	 * Ace > King > Queen > Jack > 10 > 9 > 8 > 7 > 6 > 5 > 4 > 3 > 2
	 * 
	 * @param c
	 * @return
	 */
	public int compareCard(Card c) {
		//Since Suit.UNDEFINED has the greatest value of Suits, we don't need a special check for Joker.
		//(So long as Joker is the only Card with an Undefined Suit)
		int s = suit.compareToSuit(c.suit);
		if (s == 0) {
			return value.compareToValue(c.value);
		}
		else {
			return s;
		}
	}
}