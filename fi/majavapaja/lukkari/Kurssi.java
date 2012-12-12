/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.majavapaja.lukkari;

/**
 * Kurssin nimi kertoo kurssin sisällön.
 * 
 * @author s1001069
 */
public class Kurssi {

	private String nimi;
	private int id;

	/**
	 * Luo uuden kurssin.
	 * @param nimi kurssin nimi.
	 */
	public Kurssi(String nimi) {
		this(nimi, -1);
	}

	/**
	 * Luo uuden kurssin.
	 * @param nimi kurssin nimi.
	 * @param id kurssin ID.
	 */
	public Kurssi(String nimi, int id) {
		this.nimi = nimi;
		this.id = id;
	}

	/**
	 * Palauttaa kurssin ID:n.
	 * @return kurssin ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Asettaa kurssille uuden ID:n.
	 * @param id Kurssin uusi ID.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Palauttaa kurssin nimen.
	 * @return Kurssin nimi.
	 */
	public String getNimi() {
		return nimi;
	}
	
	/**
	 * Asettaa kurssin nimen.
	 * @param nimi Kurssin uusi nimi.
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	@Override
	public String toString(){
		return getNimi();
	}
}
