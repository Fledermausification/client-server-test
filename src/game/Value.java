package game;

public enum Value {
	TWO   (2),
	THREE (3),
	FOUR  (4),
	FIVE  (5),
	SIX   (6),
	SEVEN (7),
	EIGHT (8),
	NINE  (9),
	TEN   (10),
	JACK  (11),
	QUEEN (12),
	KING  (13),
	ACE   (14),
	JOKER (15);
	
	public static final int NUM_VALUES = 14;
	
	private int value;
	
	private Value(int v) {
		value = v;
	}
	
	public String toString() {
		switch (this) {
		    case TWO:
		    	return "Two";
		    case THREE:
		    	return "Three";
		    case FOUR:
		    	return "Four";
		    case FIVE:
		    	return "Five";
		    case SIX:
		    	return "Six";
		    case SEVEN:
		    	return "Seven";
		    case EIGHT:
		    	return "Eight";
		    case NINE:
		    	return "Nine";
		    case TEN:
		    	return "Ten";
		    case JACK:
		    	return "Jack";
		    case QUEEN:
		    	return "Queen";
		    case KING:
		    	return "King";
		    case ACE:
		    	return "Ace";
		    case JOKER:
		    	return "Joker";
		    default:
		    	return "?";
		}
	}
	
	public int compareToValue(Value v) {
		return value - v.value;
	}
}