package fi.majavapaja.lukkari;

/**
 * Käyttäjätunnus toimii tiedonvälittäjänä käyttäjien tietoja varten.
 * 
 * @author Majavapaja
 */
public class Kayttajatunnus {
	public static final int OPPILAS = 0;
	public static final int YLLAPITAJA = 1;
	
	private String kayttajatunnus;
	private String salasana;
	private int id;
	private Oppilas oppilas;
	
        /**
         * 
         * 
         * @param kayttajatunnus käyttäjänimi.
         * @param salasana käyttäjän salasana.
         * @param oikeudet käyttäjän oikeu
         * @param oppilas 
         */
	public Kayttajatunnus(String kayttajatunnus, String salasana, Oppilas oppilas) {
		this(kayttajatunnus, salasana, oppilas, -1);
	}
	
	// TODO: Poista tämä käytöstä.
//	public Kayttajatunnus(String kayttajatunnus, String salasana) {
//		this(kayttajatunnus, salasana, null, -1);
//	}
	
	public Kayttajatunnus(String kayttajatunnus, String salasana, Oppilas oppilas, int id) {
		this.kayttajatunnus = kayttajatunnus;
		this.salasana = salasana;
		this.oppilas = oppilas;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getKayttajanimi() {
		return kayttajatunnus;
	}
	
	public void setKayttajnimi(String kayttajanimi) {
		this.kayttajatunnus = kayttajanimi;
	}

	public String getSalasana() {
		return salasana;
	}
	
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	public int getOikeudet() {
		if (oppilas == null){
                    return YLLAPITAJA;
                } else {
                    return OPPILAS;
                }
	}
	
	public Oppilas getOppilas() {
		return oppilas;
	}
	
	public String toString() {
		if (oppilas == null)
			return kayttajatunnus;
		return String.format("%s (%s)", kayttajatunnus, oppilas);
	}
}
