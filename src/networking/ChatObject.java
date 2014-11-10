package networking;

import java.io.Serializable;

public class ChatObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String         username;
	private String         message;
	private ChatObjectType type;
	
	public ChatObject(String u, String m, ChatObjectType t) {
		username = u;
		message  = m;
		type     = t;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ChatObjectType getType() {
		return type;
	}
}