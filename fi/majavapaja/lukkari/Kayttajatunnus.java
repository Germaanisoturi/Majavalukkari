package fi.majavapaja.lukkari;

/**
 * Käyttäjätunnus toimii tiedonvälittäjänä käyttäjien tietoja varten.
 *
 * @author Majavapaja
 */
public class Kayttajatunnus {

    public static final int OPPILAS = 0;
    public static final int YLLAPITAJA = 1;
    private String kayttajanimi;
    private String salasana;
    private int id;
    private Oppilas oppilas;

    /**
     * Luo käyttäjätunnuksen.
     *
     * @param kayttajatunnus käyttäjänimi.
     * @param salasana käyttäjän salasana.
     * @param oppilas oppilaan tiedot.
     */
    public Kayttajatunnus(String kayttajatunnus, String salasana, Oppilas oppilas) {
        this(kayttajatunnus, salasana, oppilas, -1);
    }

    /**
     * Luo käyttäjätunnuksen id:llä varustettuna tietokannan päivityksiä varten.
     *
     * @param kayttajatunnus käyttäjänimi.
     * @param salasana käyttäjän salasana.
     * @param oppilas oppilaan tiedot.
     * @param id oppilaan pääavain kannassa.
     */
    public Kayttajatunnus(String kayttajatunnus, String salasana, Oppilas oppilas, int id) {
        this.kayttajanimi = kayttajatunnus;
        this.salasana = salasana;
        this.oppilas = oppilas;
        this.id = id;
    }

    /**
     * Palauttaa käyttäjätunnuksen kannan pääavaimen.
     *
     * @return käyttäjätunnuksen kannan pääavain.
     */
    public int getId() {
        return id;
    }

    /**
     * Palauttaa käyttäjän tunnuksen.
     *
     * @return käyttäjätunnuksen nimi.
     */
    public String getKayttajanimi() {
        return kayttajanimi;
    }

    /**
     * Asettaa käyttäjätunnukselle uuden salasanan.
     *
     * @param kayttajanimi asetettava käyttäjänimi.
     */
    public void setKayttajnimi(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
    }

    /**
     * Palauttaa käyttäjän salasanan.
     *
     * @return käyttäjän salasana.
     */
    public String getSalasana() {
        return salasana;
    }

    /**
     * Asettaa käyttäjätunnukselle uuden salasanan.
     *
     * @param salasana asetettava salasana.
     */
    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    /**
     * Palauttaa int arvon sen mukaan onko käyttäjä oppilas vai ei.
     *
     * @return käyttäjän oikeudet.
     */
    public int getOikeudet() {
        if (oppilas == null) {
            return YLLAPITAJA;
        } else {
            return OPPILAS;
        }
    }

    /**
     * Palauttaa käyttäjän oppilastiedot omassa dataoliossaan.
     *
     * @return oppilaan tiedot.
     */
    public Oppilas getOppilas() {
        return oppilas;
    }

    @Override
    public String toString() {
        if (oppilas == null) {
            return kayttajanimi;
        }
        return String.format("%s (%s)", kayttajanimi, oppilas);
    }
}
