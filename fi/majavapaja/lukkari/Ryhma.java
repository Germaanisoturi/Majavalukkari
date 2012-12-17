/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.majavapaja.lukkari;

/**
 * Käyttäjätunnus toimii tiedonvälittäjänä käyttäjien tietoja varten.
 * 
 * @author Majavapaja
 */
public class Ryhma {
	private String nimi;
    private int id;
    
    /**
     * Luo uuden ryhmän ilman ID:tä.
     * 
     * @param nimi ryhmän nimi
     */
    public Ryhma(String nimi) {
    	this(nimi, -1);
    }
    
    /**
     * Luo uuden ryhmän.
     * 
     * @param nimi ryhmän nimi
     * @param id ryhmän ID
     */
    public Ryhma(String nimi, int id) {
    	this.nimi = nimi;
    	this.id = id;
	}

    /**
     * Palauttaa ryhmän nimen.
     * 
     * @return ryhmän nimi
     */
	public String getNimi(){
        return nimi;
    }
	
	/**
	 * Asettaa ryhmän nimen.
	 * 
	 * @param nimi ryhmän nimi
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
    
	/**
	 * Palauttaa ryhmän ID:n
	 * 
	 * @return ryhmän ID
	 */
    public int getId(){
        return id;
    }
    
    public String toString() {
    	return nimi;
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
}
