package gui;

import game.Card;
import game.Hand;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import networking.Client;

public class GameFrame extends JFrame {
	//private JTextField messageInput;
	private CardPanel cardPanel;
	private ChatPanel chatPanel;
	
	private Client client;
	private String username;
	
	public GameFrame(Client c, String u) {
		client = c;
		username = u;
		setupFrame();
	}
	
	private void setupFrame() {
		setTitle("Card Game");
	    setSize(1500, 1500);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    //Setup the ChatPanel
	    chatPanel = new ChatPanel(client, username);
        
	    //Setup the CardPanel
        cardPanel = new CardPanel(client, username);
        
        //messageInput = new JTextField();
        //messageInput.setDocument(new JTextFieldLimit(80));
		
        //Setup the JPanel that holds everything in it
		JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Add everything to the JPanel
        panel.add(cardPanel, BorderLayout.CENTER);
        panel.add(chatPanel, BorderLayout.EAST);
        //panel.add(messageInput, BorderLayout.SOUTH);
        add(panel);
        
        pack();
	    setVisible(true);
	}
	
	public void paintHand() {
		cardPanel.repaint();
	}
	
	public void addMessage(String message) {
		chatPanel.addMessage(message);;
	}
}