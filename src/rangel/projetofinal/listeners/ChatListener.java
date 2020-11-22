package rangel.projetofinal.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * @implNote Class that acts as a listener to chat events;
 */
public class ChatListener implements ActionListener{

	
	/* PROPERTIES **/
	ChatListenerDelegate delegate;
	
	/* GETTERS AND SETTERS **/
	public ChatListenerDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(ChatListenerDelegate delegate) {
		this.delegate = delegate;
	}
	
	/* INIT **/
	public  ChatListener(ChatListenerDelegate delegate) {
		setDelegate(delegate);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		delegate.sendButtonClicked();
		
	}

	
	
	
	
	
	
}
