package fi.majavapaja.lukkari;

public class Kayttajatunnus {
	private String kayttajatunnus;
	private String salasana;
	private int oikeudet;
	private int id;
	private Oppilas oppilas;
	
	public Kayttajatunnus(String kayttajatunnus, String salasana, int oikeudet, Oppilas oppilas) {
		this(kayttajatunnus, salasana, oikeudet, oppilas, -1);
	}
	
	// TODO: Poista tämä käytöstä.
	public Kayttajatunnus(String kayttajatunnus, String salasana, int oikeudet) {
		this(kayttajatunnus, salasana, oikeudet, null, -1);
	}
	
	public Kayttajatunnus(String kayttajatunnus, String salasana, int oikeudet, Oppilas oppilas, int id) {
		this.kayttajatunnus = kayttajatunnus;
		this.salasana = salasana;
		this.oikeudet = oikeudet;
		this.oppilas = oppilas;
		this.id = id;
	}

    public Kayttajatunnus(String kayttajatunnus, String salasana, int oikeudet) {
        this.kayttajatunnus = kayttajatunnus;
        this.salasana = salasana;
        this.oikeudet = oikeudet;
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
		return oikeudet;
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
