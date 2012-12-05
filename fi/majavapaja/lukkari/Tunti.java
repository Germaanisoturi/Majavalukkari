/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.majavapaja.lukkari;

/**
 * 
 * @author SAMI
 */
public class Tunti {
	String viikonpaiva;
	int alkuklo;
	int loppuklo;
	int id;
	Kurssi kurssi;

	public Tunti(String viikonpaiva, int alkuklo, int loppuklo, Kurssi kurssi) {
		this.viikonpaiva = viikonpaiva;
		this.alkuklo = alkuklo;
		this.loppuklo = loppuklo;
		this.kurssi = kurssi;
	}

	public Tunti(int id, String viikonpaiva, int alkuklo, int loppuklo, Kurssi kurssi) {
		this.id = id;
		this.viikonpaiva = viikonpaiva;
		this.alkuklo = alkuklo;
		this.loppuklo = loppuklo;
		this.kurssi = kurssi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getViikonpaiva() {
		return viikonpaiva;
	}

	public int getAlkuklo() {
		return alkuklo;
	}

	public int getLoppuklo() {
		return loppuklo;
	}

	public Kurssi getKurssi() {
		// TODO Auto-generated method stub
		return null;
	}
}
