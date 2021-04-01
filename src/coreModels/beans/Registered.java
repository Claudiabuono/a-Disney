package coreModels.beans;

public class Registered extends UserBean{

	private static final long serialVersionUID = -7565552670713011081L;
	private String name;
	private String cognome;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
