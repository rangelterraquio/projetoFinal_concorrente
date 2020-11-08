package rangel.projetofinal.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.text.StyledEditorKit.BoldAction;

import rangel.projetofinal.models.Message;
import rangel.projetofinal.models.Option;
import rangel.projetofinal.views.View;

public class Client {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	
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



	public static void main(String[] args) {

		
		
		
	}
	
	
	
	public void connect() throws UnknownHostException, IOException {
		
		
		
		Socket socket = new Socket("localhost", ServerSupplies.PORT);
		
		setOut(new ObjectOutputStream(socket.getOutputStream()));
		setIn(new ObjectInputStream(socket.getInputStream()));
		
		while(true) {
			
			Message serverInput;
			try {
				serverInput = (Message) getIn().readObject();
				
				Boolean isNewUser = serverInput.getMsg().contentEquals(ServerSupplies.NAMEREQUIRED);
				Boolean isNameInvalid = serverInput.getMsg().contentEquals(ServerSupplies.INVALIDNAME);
				Boolean isUserAccepted = serverInput.getMsg().contentEquals(ServerSupplies.NAMEACCEPTED);
				
				if(isNewUser) {
					String userName = View.showInputText("User Name Required", "What is your name?");
							
					getOut().writeObject(new Message(Option.NONE, userName));
				
				}else if (isNameInvalid) {
					View.showErrorMSG("Name Invalid", "Name invalid or already exist on database, try other one!");
					String userName = View.showInputText("User Name Required", "What is your name?");
					getOut().writeObject(new Message(Option.NONE, userName));		
				}else if (isUserAccepted) {
					
					
					//abilitamos o textfield
					
					
				}else {
					//mostraremos as msg de todos os outros clientes
		
				}
				
				
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	

}
