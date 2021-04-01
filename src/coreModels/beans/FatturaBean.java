package coreModels.beans;

import java.math.RoundingMode;

public class FatturaBean implements java.io.Serializable{

	private static final long serialVersionUID = -7048837643544538726L;
	private java.util.List <coreModels.beans.Order> prodotti;
	private int cod;
	private java.util.GregorianCalendar date;
	private coreModels.beans.Adress shipping;
	private Registered user;
	
	public java.util.List<coreModels.beans.Order> getProdotti() {
		return prodotti;
	}
	public void setProdotti(java.util.List<coreModels.beans.Order> prodotti) {
		this.prodotti = prodotti;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public java.util.GregorianCalendar getDate() {
		return date;
	}
	public void setDate(java.util.GregorianCalendar date) {
		this.date = date;
	}
	public coreModels.beans.Adress getShipping() {
		return shipping;
	}
	public void setShipping(coreModels.beans.Adress shipping) {
		this.shipping = shipping;
	}
	public Registered getUser() {
		return user;
	}
	public void setUser(Registered user) {
		this.user = user;
	}
	public int size () {
		return prodotti.size();
	}
	public java.math.BigDecimal getTotal () {
		double total = 0;
		java.util.List<Order> list = prodotti;
		
		for (Order o : list) {
			coreModels.beans.ProductBean item = o.getProduct();
			
			total = total + o.getQty()*(item.getPricewithIva().doubleValue());
		}
		
		return new java.math.BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
	}
	@Override
	public String toString() {
		return "Dettagli = [cod=" + cod + ", date=" + date + ", shipping=" + shipping + "]"+"\n prodotti=" + prodotti 
				+ ", user=" + user;
	}
}
