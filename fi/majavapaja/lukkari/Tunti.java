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
	private String viikonpaiva;
	private int alkuklo;
	private int loppuklo;
	private int id;
	private Kurssi kurssi;
	private Ryhma ryhma;

	/**
	 * Luo uuden kurssin ilman ID:tä
	 * 
	 * @param viikonpaiva
	 *            Viikonpäivä jolloin tunti pidetään.
	 * @param alkuklo
	 *            Tunnin alkuaika.
	 * @param loppuklo
	 *            Tunnin loppuaika.
	 * @param kurssi
	 *            Tämän tunnin kurssi.
	 */
	public Tunti(String viikonpaiva, int alkuklo, int loppuklo, Kurssi kurssi, Ryhma ryhma) {
		this(viikonpaiva, alkuklo, loppuklo, kurssi, ryhma, -1);
	}

	/**
	 * Luo uuden kurssin.
	 * 
	 * @param viikonpaiva
	 *            Viikonpäivä jolloin tunti pidetään.
	 * @param alkuklo
	 *            Tunnin alkuaika.
	 * @param loppuklo
	 *            Tunnin loppuaika.
	 * @param kurssi
	 *            Tämän tunnin kurssi.
	 * @param id
	 *            Tunnin ID.
	 */
	public Tunti(String viikonpaiva, int alkuklo, int loppuklo, Kurssi kurssi, Ryhma ryhma, int id) {
		this.viikonpaiva = viikonpaiva;
		this.alkuklo = alkuklo;
		this.loppuklo = loppuklo;
		this.kurssi = kurssi;
		this.ryhma = ryhma;
		this.id = id;
	}

	/**
	 * Palauttaa tunnin ID:n
	 * 
	 * @return Tunnin ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Asettaa uuden arvon ID:lle
	 * 
	 * @param id
	 *            uusi ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Palauttaa tunnin viikonpäivän
	 * 
	 * @return tunnin viikonpäivä.
	 */
	public String getViikonpaiva() {
		return viikonpaiva;
	}

	/**
	 * Palauttaa tunnin alkamisajan.
	 * 
	 * @return Tunnin alkamisaika.
	 */
	public int getAlkuklo() {
		return alkuklo;
	}

	/**
	 * Palauttaa tunnin päättymisajan.
	 * 
	 * @return Tunnin päättymisaika.
	 */
	public int getLoppuklo() {
		return loppuklo;
	}

	/**
	 * Palauttaa tunnin kurssin.
	 * 
	 * @return Tunnin kurssi.
	 */
	public Kurssi getKurssi() {
		return kurssi;
	}

	/**
	 * Palauttaa tunnin ryhman.
	 * 
	 * @return Tunnint ryhma.
	 */
	public Ryhma getRyhma() {
		return ryhma;
	}

	@Override
	public String toString() {
		return viikonpaiva + " " + alkuklo + " - " + loppuklo + " " + kurssi.toString() + " ryhmällä " + ryhma.toString();
	}
}
