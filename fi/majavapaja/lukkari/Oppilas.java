package fi.majavapaja.lukkari;

/**
 * Oppilas on oppilas.
 * 
 * @author Henry Heikkinen
 */
public class Oppilas {
	private int id;
	private String etunimi;
	private String sukunimi;
	private Ryhma ryhma;

	/**
	 * Luo uuden oppilaan annetuilla tiedoilla.
	 * 
	 * @param etunimi
	 *            oppilaan etunimi
	 * @param sukunimi
	 *            oppilaan sukunimi
	 * @param ryhma
	 *            oppilaan ryhmä
	 */
	public Oppilas(String etunimi, String sukunimi, Ryhma ryhma) {
		this(etunimi, sukunimi, ryhma, -1);
	}

	/**
	 * Luo uuden oppilaan annetuilla tiedoilla.
	 * 
	 * @param etunimi
	 *            oppilaan etunimi
	 * @param sukunimi
	 *            oppilaan sukunimi
	 * @param ryhma
	 *            oppilaan ryhmä
	 * @param id
	 *            oppilaan id
	 */
	public Oppilas(String etunimi, String sukunimi, Ryhma ryhma, int id) {
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.ryhma = ryhma;
		this.id = id;
	}

	/**
	 * Palauttaa oppilaan etunimen.
	 * 
	 * @return oppilaan etunimi
	 */
	public String getEtunimi() {
		return etunimi;
	}

	/**
	 * Palauttaa oppilaan sukunimen.
	 * 
	 * @return oppilaan sukunimi
	 */
	public String getSukunimi() {
		return sukunimi;
	}

	/**
	 * Palauttaa oppilaan ryhmän.
	 * 
	 * @return oppilaan ryhmä
	 */
	public Ryhma getRyhma() {
		return ryhma;
	}

	/**
	 * Asettaa oppilaan etunimen.
	 * 
	 * @param etunimi
	 *            oppilaan etunimi
	 */
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	/**
	 * Asettaa oppilaan sukunimen.
	 * 
	 * @param sukunimi
	 *            oppilaan sukunimi
	 */
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	/**
	 * Asettaa oppilaan ryhmän.
	 * 
	 * @param ryhma
	 */
	public void setRyhma(Ryhma ryhma) {
		this.ryhma = ryhma;
	}

	/**
	 * Palauttaa opiskelijan id:n.
	 * 
	 * @return opiskelijan id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("%s %s", etunimi, sukunimi);
	}
}