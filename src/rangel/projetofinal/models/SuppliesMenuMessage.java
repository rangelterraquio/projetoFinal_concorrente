package rangel.projetofinal.models;

import java.io.Serializable;


/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * Model that represents a supplies menu message
 */
@SuppressWarnings("serial")
public class SuppliesMenuMessage implements Serializable{
	
	
	
	/* PROPERTIES **/
	private Product product = null;
	private String msg = null;
	private Option option;
	
	
	

	/* GETTERS AND SETTERS **/
	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Option getOption() {
		return option;
	}


	public void setOption(Option option) {
		this.option = option;
	}

	/** INIT */

	public SuppliesMenuMessage(Option op, String msg) {
		setOption(op);
		setMsg(msg);
	}
	
	
	
	
	
	
	

}
