/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.majavapaja.lukkari;

/**
 *
 * @author s1001069
 */
public class Ryhma {
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nimi == null) ? 0 : nimi.hashCode());
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
		Ryhma other = (Ryhma) obj;
		if (nimi == null) {
			if (other.nimi != null)
				return false;
		} else if (!nimi.equals(other.nimi))
			return false;
		return true;
	}

	private String nimi;
    private int id;
    
    public Ryhma(String nimi) {
    	this(nimi, -1);
    }
    
    public Ryhma(String nimi, int id) {
    	this.nimi = nimi;
    	this.id = id;
	}

	public String getNimi(){
        return nimi;
    }
    
    public int getId(){
        return id;
    }
    
    public String toString() {
    	return nimi;
    }
}
