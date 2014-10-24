package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.ClientConnection;

public class CardFrame extends JFrame {
	private JTextArea messageLog;
	private JTextField messageInput;
	
	private ClientConnection client;
	
	
	private String username;
	private static int clientNumber = 0;
	
	public CardFrame(ClientConnection c) {
		client = c;
		username = "Client" + clientNumber++;
		setupFrame();
	}
	
	private void setupFrame() {
		messageLog = new JTextArea();
		messageLog.setEditable(false);
        messageLog.setLineWrap(true);
		messageLog.setWrapStyleWord(true);
        messageLog.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        
        messageInput = new JTextField();
        messageInput.setDocument(new JTextFieldLimit(80));
        messageInput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = messageInput.getText();
        		if (message.length() > 0) {
        			//addMessage(username + ": " + message);
        			client.sendMessage(username + ": " + message);
        			messageInput.setText("");
        		}
        	}
        });
		
		JScrollPane scrollPane = new JScrollPane(messageLog);
		
		JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        scrollPane.getViewport().add(messageLog);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(messageInput, BorderLayout.SOUTH);
        add(panel);
        
        
        
		setTitle("Card Game");
	    setSize(1000, 1200);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);   
	    
	    setVisible(true);
	}
	
	public void addMessage(String message) {
		Calendar c = Calendar.getInstance();
		String timestamp = "\n[" + String.format("%02d", c.get(Calendar.HOUR_OF_DAY)) + ":"
		                         + String.format("%02d", c.get(Calendar.MINUTE)) + ":"
		                         + String.format("%02d", c.get(Calendar.SECOND)) + "] ";
		
		messageLog.append(timestamp + message);
	}
}