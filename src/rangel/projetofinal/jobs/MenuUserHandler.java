package rangel.projetofinal.jobs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rangel.projetofinal.core.ServerSupplies;
import rangel.projetofinal.models.Message;
import rangel.projetofinal.models.Option;
import rangel.projetofinal.models.Product;
import rangel.projetofinal.models.User;

public class MenuUserHandler extends Thread {
	
	/** Properties */
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private User user;
	
	/** Getters and Setters */
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	public MenuUserHandler(Socket soc) {
		
		setSocket(soc);
		
		
	}

	
	@Override
	public void run() {
		super.run();
		
		
		try {
			
			
			setOut(new ObjectOutputStream(getSocket().getOutputStream()));
			setIn(new ObjectInputStream(socket.getInputStream()));
		
			
			
			
			Message msg = new Message(Option.SERVER_INPUT,ServerSupplies.NAMEREQUIRED);
			
			
			//Ask to the client his userName
			getOut().writeObject(msg);
			getOut().reset();
			
			Message clientInput = (Message) getIn().readObject();
			
			if(!isNameValid(clientInput.getMsg())) {
				msg.setMsg(ServerSupplies.INVALIDNAME);
				getOut().writeObject(msg);
				getOut().reset();
				return;
			}else {
				User user = new User(clientInput.getMsg());
				setUser(user);
				ServerSupplies.users.add(user);
			}

			
			
			while(true) {
				
				try {
					
					clientInput = (Message) getIn().readObject();
					
					
					switch (clientInput.getOption()) {
					case ADD: {
						if(clientInput.getProduct() != null) {
							getUser().addProduct(clientInput.getProduct());
							getOut().writeObject(new Message(Option.SERVER_INPUT, "PRODUCT ADDED SUCESSEFULLY!!!"));
						}else {
							getOut().writeObject(new Message(Option.SERVER_INPUT, "PRODUCT INVALID!!!"));
						}
					}
					case REMOVE: {
						if(getUser().removeProduct(clientInput.getMsg())) {
							getOut().writeObject(new Message(Option.SERVER_INPUT, "PRODUCT REMOVED SUCESSEFULLY!!"));
						}else {
							getOut().writeObject(new Message(Option.SERVER_INPUT, "OPSSS! THE ID WAS NOT FOUND!!"));
						}
					}
					case LIST: {
	
						String productsMsg = "";
						
						for(Product product : getUser().getProducts()) {
							productsMsg += "- " + product.toString() + "\n";
						}
						
						if(!productsMsg.isBlank()) {
							getOut().writeObject(new Message(Option.SERVER_INPUT, productsMsg));
						}else {
							getOut().writeObject(new Message(Option.SERVER_INPUT, "THERE IS NOT ANY PRODUCT!!!"));
						}
						
						
					}
					case CHAT: {
						//MARK Fazer o chat
					
					}
					default:
						break;
					}
					
					
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	
	
	private Boolean isNameValid(String name) {
		
		if(name.isEmpty() || (name == null)) {
			return false;
		}
		
		if(nameAlreadyExist(name)) {
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	private Boolean nameAlreadyExist(String name) {
		for(User user : ServerSupplies.users) {
			if(user.getName().contentEquals(name)) {
				return false;
			}
		}
		
		return true;
	}
	
	
}
