package rangel.projetofinal.jobs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import rangel.projetofinal.core.ChatServer;
import rangel.projetofinal.models.ChatMessage;


/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * @implNote Class responsable for handler with an user on chat;
 */
public class ChatUserHandler extends Thread{

	
	
	
/** PROPERTIES */
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	/** GETTERS AND SETTERS */
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}


	
	/** INIT */
	public ChatUserHandler(Socket soc) {
		
		setSocket(soc);
		
	}

	
	@Override
	public void run() {
		super.run();
		
		
		try {
			
			
			setOut(new ObjectOutputStream(getSocket().getOutputStream()));
			setIn(new ObjectInputStream(getSocket().getInputStream()));
		
			ChatMessage msg; 
		
			ChatServer.objectsOutput.add(getOut());
					
			while(true) {
				msg = (ChatMessage) getIn().readObject();
				
				for(ObjectOutputStream out :  ChatServer.objectsOutput) {
					out.writeObject(msg);
					out.reset();
				}
			}
			

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
}
