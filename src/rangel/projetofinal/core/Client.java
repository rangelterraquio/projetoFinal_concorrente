package rangel.projetofinal.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import rangel.projetofinal.models.Message;
import rangel.projetofinal.models.Option;
import rangel.projetofinal.models.Product;
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



	public static void main(String[] args) throws UnknownHostException, IOException {

		Client client = new Client();
		client.connect();
		
	}
	
	
	
	private void connect() throws UnknownHostException, IOException {
		
		
		
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
					getOut().reset();
				}else if (isNameInvalid) {
					View.showErrorMSG("Name Invalid", "Name invalid or already exist on database, try other one!");
					String userName = View.showInputText("User Name Required", "What is your name?");
					getOut().writeObject(new Message(Option.NONE, userName));
					getOut().reset();
					
				}else if (isUserAccepted) {
					
					Integer option = getUserOption();
				
					switch (option) {
					case 0: {
//							 Option.EXIT;
						socket.close();
					}
					case 1: {
						/** ADD OPTION*/
						Product p = createProduct();
						Message msg = new Message(Option.ADD, "");
						msg.setProduct(p);
						getOut().writeObject(msg);
						getOut().reset();
						serverInput = (Message) getIn().readObject();
						View.showMsg("!!!", serverInput.getMsg());
						break;
					}
					case 2: {
						/** REMOVE OPTION*/
						String productID = getProductID();
						Message msg = new Message(Option.REMOVE, productID);
						getOut().writeObject(msg);
						getOut().reset();
						serverInput = (Message) getIn().readObject();
						View.showMsg("!!!", serverInput.getMsg());	
						break;
					}
					case 3: {
						/** LIST OPTION*/
						Message msg = new Message(Option.LIST, "");
						getOut().writeObject(msg);
						getOut().reset();
						serverInput = (Message) getIn().readObject();
						View.showMsg("!!!", serverInput.getMsg());	
						break;
					}
					case 4: {
						/** LIST OPTION*/
						break;
					}
					default:
						/** NONE OPTION*/
						break;
					}
					
					
					
					
					if(option == 0) {
						break;
					}
					
					
				}
				
				
				
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		}
		
	}
	
	private String getProductID() {
		/** GET PRODUCT ID */
		String id = View.showInputText("PRODUCT ID", "TYPE THE PRODUCT ID YOU WANT TO REMOVE:");
		
		Boolean isIDInvalid = id.isEmpty();
		
		while(isIDInvalid) {
			
			View.showErrorMSG("ID INVALID","ID CAN NOT BE EMPTY!!!");
			id = View.showInputText("PRODUCT ID", "TYPE THE PRODUCT ID YOU WANT TO REMOVE:");
			isIDInvalid = id.isEmpty();
				
		}
		return id;
	}
	
	private Product createProduct() {
		
		/** GET PRODUCT NAME */
		String name = View.showInputText("PRODUCT NAME", "TYPE THE PRODUCT NAME:");
		
		Boolean isNameInvalid = name.isEmpty();
		
		while(isNameInvalid) {
			
			View.showErrorMSG("NAME INVALID","NAME CAN NOT BE EMPTY!!!");
			name = View.showInputText("PRODUCT NAME", "TYPE THE PRODUCT NAME:");
			isNameInvalid = name.isEmpty();
				
		}
		
		/** GET PRODUCT QUANTITY */
		Integer quantity = 0;
		Boolean isQuantityInvalid = true;
		
		try {
			quantity = View.showInputNumber("PRODUCT QUANTITY", "TYPE THE PRODUCT QUANTITY:");
			
			isQuantityInvalid = quantity <= 0;
			
			while (isQuantityInvalid) {
				View.showErrorMSG("QUANTITY INVALID","QUANTITY CAN NOT BE 0 OR NEGATIVE!!!");
				quantity = View.showInputNumber("PRODUCT QUANTITY", "TYPE THE PRODUCT QUANTITY:");
				isQuantityInvalid = quantity <= 0;
			}
			
			
		} catch (NumberFormatException e) {
			
			while (isQuantityInvalid) {
				View.showErrorMSG("QUANTITY INVALID","QUANTITY CAN NOT BE 0 OR NEGATIVE!!!");
				quantity = View.showInputNumber("PRODUCT QUANTITY", "TYPE THE PRODUCT QUANTITY:");
				isQuantityInvalid = quantity <= 0;
			}
			
		}
		
		/** GET PRODUCT ID */
		String identifier = View.showInputText("PRODUCT ID", "TYPE THE PRODUCT ID:");
			
		Boolean isIDInvalid = identifier.isEmpty();
		
		//TODO: Criar lógica pra verificar se o id já exitst
		while(isIDInvalid) {
			View.showErrorMSG("ID","NAME CAN NOT BE EMPTY!!!");
			identifier = View.showInputText("PRODUCT ID", "TYPE THE ID NAME:");
			isIDInvalid = identifier.isEmpty();
		}
		
		
		return new Product(name, identifier, quantity);
	}
	
	
	private Integer getUserOption() {
		
		String menuMsg =  "1 - ADD PRODUCT\n" + "1 - ADD PRODUCT\n" + "2 - REMOVE PRODUCT\n" + "3 - LIST PRODUCT(S)\n" + "4 - CHAT\n" + "0 - EXIT\n" ;
		
		try {
			Integer option = View.showInputNumber("SELECT AN OPTION", menuMsg);
			
			return option;
			
		} catch (NumberFormatException e) {
			View.showErrorMSG("INVALID INPUT", "SELECT A OPTION BETWEEN 0 AND 4!");
			return -1;
		}
	
	}
	

}
