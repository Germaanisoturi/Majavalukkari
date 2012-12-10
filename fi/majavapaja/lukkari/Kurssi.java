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

	public Kurssi(String nimi) {
		this(nimi, -1);
	}

	public Kurssi(String nimi, int id) {
		this.nimi = nimi;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}
}
