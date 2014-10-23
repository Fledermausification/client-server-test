package game;

public enum Suit {
	SPADES    (1),
	CLUBS     (2),
	DIAMONDS  (3),
	HEARTS    (4),
	UNDEFINED (0);
	
	public static final int NUM_SUITS = 4;
	
	private int value;
	
	private Suit(int value) {
		this.value = value;
	}
}