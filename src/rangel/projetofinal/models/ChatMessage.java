package rangel.projetofinal.models;

import java.io.Serializable;
/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * @implNote Model that represents a chat message
 */
@SuppressWarnings("serial")
public class ChatMessage implements Serializable{

	/** PROPERTIES */

	private String msg;
	private User author;
	
	/** GETTERS AND SETTERS */

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	/** INIT */

	public ChatMessage(User author, String msg){
		setAuthor(author);
		setMsg(msg);
	}
	
}
