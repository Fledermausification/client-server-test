package gui;

import game.Card;
import game.Hand;
import game.Suit;
import game.Value;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import networking.Client;

public class CardPanel extends JPanel {
	public static final int CARD_WIDTH         = 70;
	public static final int CARD_HEIGHT        = 100;
	public static final int CARD_ROUNDED_EDGES = 5;
	public static final int GAP_BETWEEN_CARDS  = 10;
	public static final int VALUE_X_OFFSET     = 5;
	public static final int VALUE_Y_OFFSET     = 15;
	public static final int SUIT_X_OFFSET      = 30;
	public static final int SUIT_Y_OFFSET      = 55;
	
	private Client client;
	private String username;

	private JToggleButton sortBySuit;
	private JToggleButton sortByValue;

	public CardPanel(Client c, String u) {
		client = c;
		username = u;
		setupPanel();
	}
	
	private void setupPanel() {
		setPreferredSize(new Dimension(800, 800));
		//setMinimumSize(new Dimension(600, 600));
		//setMaximumSize(new Dimension(600, 600));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel panel = new JPanel();

        sortBySuit = new JToggleButton("Sort by Suit");
        sortBySuit.setSelected(true);
        sortBySuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.getHand().sortBySuit();
				sortBySuit.setSelected(true);
				sortByValue.setSelected(false);
				repaint();
			}
        });
        
        sortByValue = new JToggleButton("Sort by Value");
        sortByValue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.getHand().sortBySuit();
				sortByValue.setSelected(true);
				sortBySuit.setSelected(false);
				repaint();
			}
        });
        
        panel.add(sortBySuit);
        panel.add(sortByValue);
        add(panel, BorderLayout.SOUTH);
        
	    setVisible(true);
	    
	    repaint();
	}
	
	public void paintCard(Graphics g, Card c, int x, int y) {
		//Draw the card outline
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, CARD_WIDTH, CARD_HEIGHT, CARD_ROUNDED_EDGES, CARD_ROUNDED_EDGES);
		
		//Get the correct font colour (RED for Hearts/Diamonds, BLACK for Clubs/Spaces, PINK for Jokers)
		Suit s = c.getSuit();
		if (c.getValue().equals(Value.JOKER)) {
			g.setColor(Color.MAGENTA);
		}
		else if (s.equals(Suit.HEARTS) || s.equals(Suit.DIAMONDS)) {
			g.setColor(Color.RED);
		}
		
		//Draw the Value and Suit
		g.drawString(c.getValue().toString(), x + VALUE_X_OFFSET, y + VALUE_Y_OFFSET);
		g.drawString(c.getSuit().toString(), x + SUIT_X_OFFSET, y + SUIT_Y_OFFSET);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 20;
		int y = 630;
		
		//Sort the Hand
		Hand h = client.getHand();
		if (sortBySuit.isSelected())
			h.sortBySuit();
		else if (sortByValue.isSelected())
			h.sortByValue();
		for (Card c : h.getAll()) {
			paintCard(g, c, x, y);
			x += CardPanel.CARD_WIDTH + CardPanel.GAP_BETWEEN_CARDS;
		}
	}
}