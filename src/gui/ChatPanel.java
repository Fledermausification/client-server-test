package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import networking.ChatObject;
import networking.ChatObjectType;
import networking.Client;

public class ChatPanel extends JPanel {
	private JTextArea messageLog;
	private JTextField messageInput;
	
	private Client client;
	private String username;
	
	public ChatPanel(Client c, String u) {
		client = c;
		username = u;
		setupPanel();
	}
	
	private void setupPanel() {
		setPreferredSize(new Dimension(600, 600));
		//setMinimumSize(new Dimension(600, 600));
		//setMaximumSize(new Dimension(600, 600));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		messageLog = new JTextArea();
		messageLog.setEditable(false);
        messageLog.setLineWrap(true);
		messageLog.setWrapStyleWord(true);
        messageLog.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        messageLog.setText("Welcome to the Card Game " + username);
        
        messageInput = new JTextField();
        messageInput.setDocument(new JTextFieldLimit(150));
        messageInput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = messageInput.getText();
        		if (message.length() > 0) {
        			client.writeObject(new ChatObject(username, message, ChatObjectType.USER_MESSAGE));
        			messageInput.setText("");
        		}
        	}
        });
		
		JScrollPane scrollPane = new JScrollPane(messageLog);
		DefaultCaret caret = (DefaultCaret)messageLog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
        scrollPane.getViewport().add(messageLog);
        add(scrollPane, BorderLayout.CENTER);
        add(messageInput, BorderLayout.SOUTH);
        
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