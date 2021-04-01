package coreModels.beans;

import java.io.Serializable;
import java.math.RoundingMode;

public class Order implements Serializable {

	@Override
	public String toString() {
		return "Order [product=" + product + ", qty=" + qty + "]\n";
	}
	private static final long serialVersionUID = -7625196350124530857L;
	private ProductBean product;
	private int qty;
	
	public Order () {
		
	}
	
	public Order (int id) {
		product = new ProductBean (id);
	}
	
	public Order (ProductBean e, int quantity) {
		product = e;
		qty = quantity;
	}
	
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	/*-----	utilities ------*/
	public java.math.BigDecimal getTotal () {
		return new java.math.BigDecimal(product.getPricewithIva().doubleValue()*qty).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
