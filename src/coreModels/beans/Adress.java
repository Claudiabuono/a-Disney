package coreModels.beans;

public class Adress implements java.io.Serializable {

	private static final long serialVersionUID = 6021946553406606291L;
	private String nation;
	private String street;
	private int nCv;
	private int CAP;
	private String province;
	private String citta;
	private int codice;
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getnCv() {
		return nCv;
	}
	public void setnCv(int nCv) {
		this.nCv = nCv;
	}
	public int getCAP() {
		return CAP;
	}
	public void setCAP(int cAP) {
		CAP = cAP;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	public String toString (){
		return "Indirizzo: "+street+", "+nCv +"; CAP: " +CAP +" citta: " +citta;
	}
}
