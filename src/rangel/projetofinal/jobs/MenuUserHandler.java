package rangel.projetofinal.jobs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rangel.projetofinal.core.ChatClient;
import rangel.projetofinal.core.SuppliesServer;
import rangel.projetofinal.models.SuppliesMenuMessage;
import rangel.projetofinal.models.Option;
import rangel.projetofinal.models.Product;
import rangel.projetofinal.models.User;


/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 *@implNote Class responsable for handler with an user on supplies menu;
 */
public class MenuUserHandler extends Thread {
	
	/** PROPERTIES */
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private User user;
	
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
		
			
			
			
			SuppliesMenuMessage msg = new SuppliesMenuMessage(Option.SERVER_INPUT,SuppliesServer.NAMEREQUIRED);
			
			
			//Ask to the client his userName
			getOut().writeObject(msg);
			getOut().reset();
			
			SuppliesMenuMessage clientInput = (SuppliesMenuMessage) getIn().readObject();
			
			
			while((!isNameValid(clientInput.getMsg()))) {
				msg.setMsg(SuppliesServer.INVALIDNAME);
				getOut().writeObject(msg);
				getOut().reset();
				clientInput = (SuppliesMenuMessage) getIn().readObject();
			}
			
		
				User user = new User(clientInput.getMsg());
				setUser(user);
				SuppliesServer.users.add(user);
				msg.setMsg(SuppliesServer.NAMEACCEPTED);
				getOut().writeObject(msg);
				getOut().reset();
				
		

			
			
			while(true) {
				
				try {
					
					clientInput = (SuppliesMenuMessage) getIn().readObject();
					
					
					switch (clientInput.getOption()) {
					case ADD: {
						if(clientInput.getProduct() != null && !productAlreadyExist(clientInput.getProduct())) {
							getUser().addProduct(clientInput.getProduct());
							getOut().writeObject(new SuppliesMenuMessage(Option.SERVER_INPUT, "PRODUCT ADDED SUCESSEFULLY!!!"));
						}else {
							getOut().writeObject(new SuppliesMenuMessage(Option.SERVER_INPUT, "PRODUCT INVALID!!!"));
						}
						getOut().reset();
						break;
					}
					case REMOVE: {
						if(getUser().removeProduct(clientInput.getMsg())) {
							getOut().writeObject(new SuppliesMenuMessage(Option.SERVER_INPUT, "PRODUCT REMOVED SUCESSEFULLY!!"));
						}else {
							getOut().writeObject(new SuppliesMenuMessage(Option.SERVER_INPUT, "OPSSS! THE ID WAS NOT FOUND!!"));
						}
						getOut().reset();
						break;
					}
					case LIST: {
	
						String productsMsg = "";
						
						for(Product product : getUser().getProducts()) {
							productsMsg += "- " + product.toString() + "\n";
						}
						
						if(!productsMsg.isBlank()) {
							getOut().writeObject(new SuppliesMenuMessage(Option.SERVER_INPUT, productsMsg));
						}else {
							getOut().writeObject(new SuppliesMenuMessage(Option.SERVER_INPUT, "THERE IS NOT ANY PRODUCT!!!"));
						}
						getOut().reset();
						break;
					}
					case CHAT: {
						ChatClient chat = new ChatClient(getUser());
						chat.start();
						break;
					}
					default:
						break;
					}
					
					
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				
			}
			
			
			

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	

	
	
	/**
	 * Method to verify if the given name is valid
	 * @param name
	 * @return a boolean saying if it's valid or not
	 */
	private Boolean isNameValid(String name) {
		
		if(name.isEmpty() || (name == null)) {
			return false;
		}
		
		if(nameAlreadyExist(name)) {
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	/**
	 * Method to verify if the given name already exists
	 * @param name
	 * @return a boolean saying if the name already exists or not
	 */
	private Boolean nameAlreadyExist(String name) {
		for(User user : SuppliesServer.users) {
			if(user.getName().contentEquals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Method to verify if the given product already exists
	 * @param name
	 * @return a boolean saying if the product already exists or not
	 */
	private Boolean productAlreadyExist(Product product) {
		for(Product prod : user.getProducts()) {
			if(prod.getId().contentEquals(product.getId())) {
				return true;
			}
		}
		return false;
	}
}
