package coreModels.beans;

import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {

	private static final long serialVersionUID = 572202513877133735L;
	private Map<Integer, Order> products;
	
	public Cart() {
		products = new HashMap<Integer, Order>();
	}
	
	public void addOrder (Order o) {
		products.put(o.getProduct().getCode(), o);
	}
	
	public void deleteProduct(int id) {
		products.remove(id);
	}
	
	public Order getOrder (int code) {
		return products.get(code);
	}
	
	public java.util.ArrayList<Order> getOrders() {
		return new java.util.ArrayList <Order>(products.values());
	}
	
	public int [] getCodes () {
		java.util.Set<Integer> set = products.keySet();
		return set.stream().mapToInt(Integer::intValue).toArray();
	}
	
	/*---- utilities -----*/
	
	public java.math.BigDecimal getTotal () {
		double total = 0;
		Collection<Order> list = products.values();
		
		for (Order o : list) {
			coreModels.beans.ProductBean item = o.getProduct();
			
			total = total + o.getQty()*(item.getPricewithIva().doubleValue());
		}
		
		return new java.math.BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public java.math.BigDecimal getTotalWithoutIva () {
		double total = 0;
		Collection<Order> list = products.values();
		
		for (Order o : list) {
			coreModels.beans.ProductBean item = o.getProduct();
			
			total = total + o.getQty() * (item.getPriceSenzaIva().doubleValue());
		}
		
		return new java.math.BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public java.math.BigDecimal getTotalIva () {
		double total = 0;
		Collection<Order> list = products.values();
		
		
		for (Order o : list) {
			coreModels.beans.ProductBean item = o.getProduct();
			
			total = total + o.getQty()*(item.calculateIva().doubleValue());
		}
		
		return new java.math.BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public int size () {
		return products.size();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return products.isEmpty();
	}
}
