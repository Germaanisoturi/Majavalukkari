package fi.majavapaja.lukkari;

/**
 * Oppilas on oppilas.
 * 
 * @author Henry Heikkinen
 */
public class Oppilas {

    /**
     * Oppilaan id
     */
    private int id;
    /**
     * Oppilaan etunimi
     */
    private String etunimi;
    /**
     * Oppilaan sukunimi
     */
    private String sukunimi;
    /**
     * Oppilaan ryhma
     */
    private Ryhma ryhma;

    /**
     * Luo uuden oppilaan annetuilla tiedoilla.
     *
     * @param etunimi oppilaan etunimi
     * @param sukunimi oppilaan sukunimi
     * @param ryhma oppilaan ryhmä
     */
    public Oppilas(String etunimi, String sukunimi, Ryhma ryhma) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.ryhma = ryhma;
    }

    public Oppilas(String etunimi, String sukunimi, Ryhma ryhma, int id) {
    	this.id = id;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.ryhma = ryhma;
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
     * Palauttaa opiskelijan id:n.
     *     
     * @return opiskelijan id
     */
    public int getId() {
        return id;
    }

    /**
     * Asettaa opiskelijan id:n.
     *     
     * @param id opiskelijan uusi id
     */
    public void setId(int id) {
        id = id;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", etunimi, sukunimi, ryhma);
    }
}