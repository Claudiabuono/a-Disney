package coreModels.beans;

public class Registered extends UserBean{

	private static final long serialVersionUID = -7565552670713011081L;
	private String name;
	private String cognome;
	public Registered(){ }
	public Registered(String name, String cognome, String login, String password){
		super(login, password);
		this.cognome= cognome;
		this.name= name;

	}
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
