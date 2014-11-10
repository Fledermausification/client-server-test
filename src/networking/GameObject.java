package networking;

import game.Card;

import java.io.Serializable;

public class GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Card           card;
	private GameObjectType type;
	
	public GameObject(Card c, GameObjectType t) {
		card = c;
		type = t;
	}
	
	public Card getCard() {
		return card;
	}
	
	public GameObjectType getType() {
		return type;
	}
}