package fi.majavapaja.lukkari;

/**
 * Käyttäjätunnus toimii tiedonvälittäjänä käyttäjien tietoja varten.
 *
 * @author Majavapaja
 */
public class Kayttajatunnus {
	/**
	 * Käyttäjätunnuksen oikeuksien arvo, joka osoittaa käyttäjän olevan oppilas.
	 */
	public static final int OPPILAS = 0;
	/**
	 * Käyttäjätunnuksen oikeuksien arvo, joka osoittaa käyttäjän olevan ylläpitäjä.
	 */
	public static final int YLLAPITAJA = 1;
	
	private String kayttajatunnus;
	private String salasana;
	private int id;
	private Oppilas oppilas;
	
	/**
	 * Luo uuden käyttäjätunnuksen ilman ID:tä.
	 * 
	 * @param kayttajatunnus käyttäjänimi
	 * @param salasana käyttäjän salasana.
	 * @param oppilas käyttäjätunnukseen yhdistettävä oppilas
	 */
	public Kayttajatunnus(String kayttajatunnus, String salasana, Oppilas oppilas) {
		this(kayttajatunnus, salasana, oppilas, -1);
	}
	
	/**
	 * Luo uuden käyttäjätunnuksen.
	 * 
	 * @param kayttajatunnus käyttäjänimi
	 * @param salasana käyttäjän salasana.
	 * @param oppilas käyttäjätunnukseen yhdistettävä oppilas
	 * @param id käyttäjätunnuksen ID
	 */
	public Kayttajatunnus(String kayttajatunnus, String salasana, Oppilas oppilas, int id) {
		this.kayttajatunnus = kayttajatunnus;
		this.salasana = salasana;
		this.oppilas = oppilas;
		this.id = id;
	}
	
	/**
	 * Palauttaa käyttäjätunnuksen ID:n.
	 * 
	 * @return käyttjätunnuksen ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Palauttaa käyttäjän käyttäjänimen.
	 * 
	 * @return käyttäjän käyttäjänimi
	 */
	public String getKayttajanimi() {
		return kayttajatunnus;
	}
	
	/**
	 * Asettaa käyttäjän käyttäjänimen.
	 * 
	 * @param kayttajanimi uusi käyttäjänimi
	 */
	public void setKayttajnimi(String kayttajanimi) {
		this.kayttajatunnus = kayttajanimi;
	}

	/**
	 * Palauttaa käyttäjän salasanan.
	 * 
	 * @return käyttäjän salasana
	 */
	public String getSalasana() {
		return salasana;
	}
	
	/**
	 * Asettaa käyttäjän salasanan.
	 * 
	 * @param salasana uusi salasana
	 */
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	/**
	 * Palauttaa käyttäjän oikeudet.
	 * 
	 * @return Kayttajatunnus.YLLAPITAJA tai Kayttajatunnus.OPPILAS
	 */
	public int getOikeudet() {
		if (oppilas == null){
			return YLLAPITAJA;
		} else {
			return OPPILAS;
		}
	}
	
	/**
	 * Palauttaa käyttäjätunnukseen liitetyn oppilaan.
	 * 
	 * @return käyttäjätunnukseen liitetty oppilas
	 */
	public Oppilas getOppilas() {
		return oppilas;
	}
	
	public String toString() {
		if (oppilas == null)
			return kayttajatunnus;
		return String.format("%s (%s)", kayttajatunnus, oppilas);
	}
}
