package rangel.projetofinal.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import rangel.projetofinal.models.SuppliesMenuMessage;
import rangel.projetofinal.models.Option;
import rangel.projetofinal.models.Product;
import rangel.projetofinal.views.View;
/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * @implNote Class responsable for Client's supplies Menu;
 */
public class SuppliesClient {

	/** PROPERTIES */

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String currentUserName;
	
	
	/** GETTERS AND SETTERS */

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

		SuppliesClient client = new SuppliesClient();
		client.connect();
		
	}
	
	
	
	/**
	 * Method to connect to the server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void connect() throws UnknownHostException, IOException {
		
		
		Socket socket = new Socket("localhost", SuppliesServer.PORT);
		
		setOut(new ObjectOutputStream(socket.getOutputStream()));
		setIn(new ObjectInputStream(socket.getInputStream()));
		
		while(true) {
			
			SuppliesMenuMessage serverInput;
			try {
				serverInput = (SuppliesMenuMessage) getIn().readObject();
				
				Boolean isNewUser = serverInput.getMsg().contentEquals(SuppliesServer.NAMEREQUIRED);
				Boolean isNameInvalid = serverInput.getMsg().contentEquals(SuppliesServer.INVALIDNAME);
				Boolean isUserAccepted = serverInput.getMsg().contentEquals(SuppliesServer.NAMEACCEPTED);
				
				if(isNewUser) {
					String userName = View.showInputText("User Name Required", "What is your name?");
					currentUserName = userName;
					getOut().writeObject(new SuppliesMenuMessage(Option.NONE, userName));
					getOut().reset();
				}else if (isNameInvalid) {
					View.showErrorMSG("Name Invalid", "Name invalid or already exist on database, try other one!");
					String userName = View.showInputText("User Name Required", "What is your name?");
					currentUserName = userName;
					getOut().writeObject(new SuppliesMenuMessage(Option.NONE, userName));
					getOut().reset();
					
				}else if (isUserAccepted) {
					
					while(true) {
						Integer option = getUserOption();
						
						switch (option) {
						case 0: {
							/** EXIT OPTION*/
							socket.close();
							break;
						}
						case 1: {
							/** ADD OPTION*/
							Product p = createProduct();
							SuppliesMenuMessage msg = new SuppliesMenuMessage(Option.ADD, "");
							msg.setProduct(p);
							getOut().writeObject(msg);
							getOut().reset();
							serverInput = (SuppliesMenuMessage) getIn().readObject();
							View.showMsg("!!!", serverInput.getMsg());
							break;
						}
						case 2: {
							/** REMOVE OPTION*/
							String productID = getProductID();
							SuppliesMenuMessage msg = new SuppliesMenuMessage(Option.REMOVE, productID);
							getOut().writeObject(msg);
							getOut().reset();
							serverInput = (SuppliesMenuMessage) getIn().readObject();
							View.showMsg("!!!", serverInput.getMsg());	
							break;
						}
						case 3: {
							/** LIST OPTION*/
							SuppliesMenuMessage msg = new SuppliesMenuMessage(Option.LIST, "");
							getOut().writeObject(msg);
							getOut().reset();
							serverInput = (SuppliesMenuMessage) getIn().readObject();
							View.showMsg("!!!", serverInput.getMsg());	
							break;
						}
						case 4: {
							/** CHAT OPTION*/
							SuppliesMenuMessage msg = new SuppliesMenuMessage(Option.CHAT, "");
							getOut().writeObject(msg);
							getOut().reset();
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
					
					
				}
				
				
				
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		}
		
	}
	
	/**
	 * Method to get Product ID
	 * @return Product ID
	 */
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
	/**
	 * Method to create a product
	 * @return Product
	 */
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
	
	
	/**
	 * Method to get user's option
	 * @return option chosen
	 */
	/**
	 * @return
	 */
	private Integer getUserOption() {
		
		String menuMsg = "1 - ADD PRODUCT\n" + "2 - REMOVE PRODUCT\n" + "3 - LIST PRODUCT(S)\n" + "4 - CHAT\n" + "0 - EXIT\n" ;
		
		try {
			Integer option = View.showInputNumber(currentUserName + ": SELECT AN OPTION", menuMsg);
			
			return option;
			
		} catch (NumberFormatException e) {
			View.showErrorMSG("INVALID INPUT", "SELECT A OPTION BETWEEN 0 AND 4!");
			return -1;
		}
	
	}
	

}
