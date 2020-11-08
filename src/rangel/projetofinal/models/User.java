package rangel.projetofinal.models;

import java.util.ArrayList;
import java.util.UUID;

public class User {

	
	private String name;
	private UUID identifier;
	private ArrayList<Product> products;
	
	
	
	public User(String name) {
		setName(name);
		identifier = new UUID(3,3);
		
		
		
	}



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
			if(product.getCode().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}
	
}
