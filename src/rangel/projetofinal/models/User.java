package rangel.projetofinal.models;

import java.util.ArrayList;
import java.util.UUID;



/**
 * @author Rangel Cardoso Dias;
 * @implNote classe than represents an User;
 */
public class User {

	/** PROPERTIES */

	private String name;
	private UUID identifier;
	private ArrayList<Product> products;
	
	
	/** CONSTRUCTOR */

	public User(String name) {
		setName(name);
		identifier = new UUID(3,3);
		
		
		
	}


	/** GETTERS AND SETTERS */

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public UUID getIdentifier() {
		return identifier;
	}


	public ArrayList<Product> getProducts() {
		return products;
	}


	public Boolean removeProduct(String id) {
		
		for(Product product: getProducts()) {
			if(product.getId().equals(id)) {
				return getProducts().remove(product);
			}
		}
		return false;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	
	@Override
	public String toString() {
		return "NAME: " + getName() + ", ID: " + getIdentifier().toString() + ";";
	}
	
}
