package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

public class ServerThread extends Thread {
	private List <?> others;
	private BufferedReader in;
	private PrintWriter    out;
	
	public ServerThread(BufferedReader in, PrintWriter out) {
		this.in  = in;
		this.out = out;
	}
	
	public void run() {
		while (true) {
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}