package game;

public class Card {
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
		return value + " of " + suit;
	}
}