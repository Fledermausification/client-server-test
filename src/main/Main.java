package main;

import server.Server;
import client.ClientConnection;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("args[" + i + "]: " + args[i]);
		}
		
		if (args[0].equals("-server")) {
		    Server s = new Server(1234, 3);
		    
		}
		else if (args[0].equals("-client")) {
			ClientConnection c = new ClientConnection("localhost", 1234);
		}
	}
}