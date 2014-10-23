package client;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {
	public ClientConnection(String address, int port) {
		try {
			Socket connection = new Socket(address, port);
			System.out.println("Connected to the server!");
			
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			/** Instantiate an OutputStreamWriter object with the optional character
			 * encoding.
			 */
			OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
			
			String TimeStamp = new java.util.Date().toString();
		    String process = "Calling the Socket Server on "+ address + " port " + port + " at " + TimeStamp +  (char) 13;
		    
		    /** Write across the socket connection and flush the buffer */
		    //osw.write(process);
		    //osw.flush();
		      
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}