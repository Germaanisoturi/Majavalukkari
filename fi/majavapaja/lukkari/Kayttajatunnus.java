package fi.majavapaja.lukkari;

public class Kayttajatunnus {
	private String kayttajatunnus;
	private String salasana;
	private int oikeudet;
	private int id;
	
	public Kayttajatunnus(String kayttajatunnus, String salasana, int oikeudet) {
		this.kayttajatunnus = kayttajatunnus;
		this.salasana = salasana;
		this.oikeudet = oikeudet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKayttajatunnus() {
		return kayttajatunnus;
	}

	public String getSalasana() {
		return salasana;
	}

	public int getOikeudet() {
		return oikeudet;
	}
}