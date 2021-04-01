package coreModels.beans;

import java.io.Serializable;

public class RecenzioneBean implements Serializable{

	@Override
	public String toString() {
		return "RecenzioneBean [description=" + description + ", valutazione=" + valutazione + ", name=" + name + "]";
	}
	private static final long serialVersionUID = -1922619941014946358L;
	private String description;
	private float valutazione;
	private String name;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getValutazione() {
		return valutazione;
	}
	public void setValutazione(float valutazione) {
		this.valutazione = valutazione;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(valutazione);
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
		RecenzioneBean other = (RecenzioneBean) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(valutazione) != Float.floatToIntBits(other.valutazione))
			return false;
		return true;
	}
}
