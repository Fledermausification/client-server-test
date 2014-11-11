package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import networking.Client;

public class GameFrame extends JFrame {
	private JTextField messageInput;
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
	    
	    chatPanel = new ChatPanel(client, username);
        
        messageInput = new JTextField();
        messageInput.setDocument(new JTextFieldLimit(80));
		
		JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(600, 600));

        panel.add(center, BorderLayout.CENTER);
        //panel.add(messageInput, BorderLayout.SOUTH);
        panel.add(chatPanel, BorderLayout.EAST);
        add(panel);
        
        pack();
	    setVisible(true);
	}
	
	public void addMessage(String message) {
		chatPanel.addMessage(message);;
	}
}