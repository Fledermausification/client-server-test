package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private Socket[] client;
	private PrintWriter[] out;
	private BufferedReader[] in;
	
	public Server(int port, int maxClients) {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("ServerSocket created. inet: " + ss.getInetAddress() + ", local: " + ss.getLocalSocketAddress() + ", port: " + ss.getLocalPort());
			
			int i = 0;
			client = new Socket[maxClients];
			out    = new PrintWriter[maxClients];
			in     = new BufferedReader[maxClients];
			
			while (i < maxClients) {
			    client[i] = ss.accept();
	            out[i] = new PrintWriter(client[i].getOutputStream(), true);
	            in[i]  = new BufferedReader(new InputStreamReader(client[i].getInputStream()));
	            
				System.out.println("Client connected " + i++);
			}
			
			System.out.println("All clients connected!");
			
			for (PrintWriter p : out) {
				p.println("SERVER MESSAGE - Let's have a clean figh- I mean game!");
			}
			
			while (true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
            /*String inputLine, outputLine;
            while ((inputLine = in[0].readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                if (outputLine.equals("Bye."))
                    break;
            }*/
            
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + port + " or listening for a connection");
	        System.out.println(e.getMessage());
		}
	}
}