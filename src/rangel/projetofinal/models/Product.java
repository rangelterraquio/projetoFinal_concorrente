package rangel.projetofinal.models;



/**
 * @author Rangel Cardoso Dias;
 * @implNote classe than represents a Product;
 */
public class Product {
	
	
	/** PROPERTIES */
	private String id;
	private String name;
	private Integer quantity;
	
	
	
	/** GETTERS AND SETTERS */
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	/** CONSTRUCTOR */
	public Product(String name, String id, Integer qtt) {
		setName(name);
		setId(id);
		setQuantity(qtt);
	}
	
	
	@Override
	public String toString() {
		return "NAME: " + getName() + ", QUANTITY: " + getQuantity() + ", ID: " + getId() + ";";
	}
	

}
