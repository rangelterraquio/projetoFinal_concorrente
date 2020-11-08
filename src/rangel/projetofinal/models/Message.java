package rangel.projetofinal.models;

import java.io.Serializable;

public class Message implements Serializable{
	
	
	

	private Product product = null;
	private String msg = null;
	private Option option;
	
	
	

	
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

	
	public Message(Option op, String msg) {
		setOption(op);
		setMsg(msg);
	}
	
	
	
	
	
	
	

}
