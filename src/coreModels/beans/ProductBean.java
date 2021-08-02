package coreModels.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = -2354351386783422715L;
	private int qty;
	private int code;
	private String photo;
	private String name; 
	private String description;
	private String character;
	private String tipo;
	private int category;
	private double iva;
	private double discount;
	private BigDecimal price;
	
	public ProductBean (int id) {
		code = id;
	}
	
	public ProductBean () {
		
	}
	//name, description,personaggio, url, tipo,categoria,quantity,price,iva,sconto
	public ProductBean (String nome,String  description, String personaggio, String url,String tipo, int categoria, int quantity, double price, double iva, double sconto  ) {
		this.name= nome;
		this.description= description;
		this.character= personaggio;
		this.photo= url;
		this.tipo= tipo;
		this.category= categoria;
		this.qty= quantity;
		this.discount= sconto;
	//	this.price=price;
		this.iva= iva;
		this.discount= sconto;
	}
	
	

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = setDecimal (d);
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int quantity) {
		this.qty = quantity;
	}
	
	/*------ utilities -------*/
	private BigDecimal setDecimal (double d) {
		return new BigDecimal (d).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * la funzione controlla se il prodotto � in sconto
	 * @return true � in sconto : false non � in sconto
	 */
	public boolean isinDiscount() {
		return discount > 0;
	}
	
	/**
	 * la funzione fa il calcolo dell'iva da applicare sul prodotto
	 * @return double iva
	 */
	public BigDecimal calculateIva () {
		return setDecimal(price.doubleValue()*(iva/100));
	}
	
	/**
	 * ritorna il prezzo senza sconti applicati
	 * @return prezzo + iva
	 */
	public BigDecimal getPriceSenzaSconto () {
		double iva = calculateIva ().doubleValue();
		double money = price.doubleValue();
		
		return setDecimal(iva + money);
	}
	
	public BigDecimal getPriceSenzaIva () {
		double money = price.doubleValue();
		
		return isinDiscount () ? setDecimal(money - money * (discount/100)) : price;
	}
	/**
	 * ritorna il prezzo unitario completo (iva + prezzo) tenendo conto se � stato o meno applicato uno sconto
	 * @return 
	 */
	
	public BigDecimal getPricewithIva () {
		double iva = calculateIva ().doubleValue();
		double money = price.doubleValue();
		
		return isinDiscount() ?	setDecimal (money + iva - (discount/100)*(iva + money)): getPriceSenzaSconto();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
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
		ProductBean other = (ProductBean) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductBean [qty=" + qty + ", code=" + code + ", photo=" + photo + ", name=" + name + ", description="
				+ description + ", character=" + character + ", tipo=" + tipo + ", category=" + category + ", iva="
				+ iva + ", discount=" + discount + ", price=" + price + "]";
	}


	public String toStringSenzaCodice() {
		return "ProductBean [qty=" + qty + ", photo=" + photo + ", name=" + name + ", description="
				+ description + ", character=" + character + ", tipo=" + tipo + ", category=" + category + ", iva="
				+ iva + ", discount=" + discount + ", price=" + price + "]";
	}
}
