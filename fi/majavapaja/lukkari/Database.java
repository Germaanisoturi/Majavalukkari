
package fi.majavapaja.lukkari;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author s1001069
 */
public class Database {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String connection = "jdbc:mysql://localhost/majavalukkari";

	private static Connection connect() {
		try {
			Class.forName(driver).newInstance();
			return DriverManager.getConnection(connection, "root", "");
		} catch (Exception ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * Lisätään uusi käyttäjätunnus tietokantaan.
	 * 
	 * @param kayttajatunnus lisättävä käyttäjätunnus.
	 * @return true tai false operaation onnistumisen mukaan.
	 */
	public static boolean lisaaKayttajatunnus(Kayttajatunnus kayttajatunnus) {
		Connection con = connect();
		try {
			PreparedStatement tunnusInsert = con.prepareStatement("INSERT INTO kayttajatunnus (kayttajanimi, salasana, oikeudet) VALUES (?,?,?)");
			tunnusInsert.setString(1, kayttajatunnus.getKayttajanimi());
			tunnusInsert.setString(2, kayttajatunnus.getSalasana());
			tunnusInsert.setInt(3, kayttajatunnus.getOikeudet());
			tunnusInsert.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Lisätään uusi oppilas ja käyttäjätunnus tietokantaan. Metodi suorittaa tietokantaan haun lisätyn käyttäjätunnuksen id:tä varten.
	 * 
	 * @param kayttajatunnus lisättävä käyttäjätunnus.
	 * @param uusiOppilas uuden oppilaan tiedot.
	 * @return true tai false operaation onnistumisen mukaan.
	 */
	public static boolean lisaaKayttajatunnus(Kayttajatunnus kayttajatunnus, Oppilas uusiOppilas) {
		Connection con = connect();
		try {
			if (!lisaaKayttajatunnus(kayttajatunnus)) {
				return false;
			}
			PreparedStatement haeKayttajatunnuksenId = con.prepareStatement("SELECT kayttajatunnusID FROM kayttajatunnus WHERE kayttajanimi = ?");
			haeKayttajatunnuksenId.setString(1, kayttajatunnus.getKayttajanimi());
			ResultSet rs = haeKayttajatunnuksenId.executeQuery();
			rs.next();
			int kayttajatunnusId = rs.getInt("kayttajatunnusID");
			PreparedStatement oppilasInsert = con.prepareStatement("INSERT INTO oppilas (etunimi, sukunimi, kayttajatunnus, ryhma) VALUES (?,?,?,?)");
			oppilasInsert.setString(1, uusiOppilas.getEtunimi());
			oppilasInsert.setString(2, uusiOppilas.getSukunimi());
			oppilasInsert.setInt(3, kayttajatunnusId);
			oppilasInsert.setInt(4, uusiOppilas.getRyhma().getId());
			oppilasInsert.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Lisätään uusi ryhmä tietokantaan.
	 * 
	 * @param uusiRyhma
	 *            uuden ryhmän tiedot.
	 * @return true tai false operaation onnistumisen mukaan.
	 */
	public static boolean lisaaRyhma(Ryhma uusiRyhma) {
		Connection con = connect();
		try {
			PreparedStatement ryhmaInsert = con.prepareStatement("INSERT INTO ryhma (nimi) VALUES (?)");
			ryhmaInsert.setString(1, uusiRyhma.getNimi());
			ryhmaInsert.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Lisätään uusi kurssi tietokantaan.
	 * 
	 * @param uusiKurssi
	 *            uuden kurssin tiedot.
	 * @return true tai false operaation onnistumisen mukaan.
	 */
	public static boolean lisaaKurssi(Kurssi uusiKurssi) {
		Connection con = connect();
		try {
			PreparedStatement kurssiInsert = con.prepareStatement("INSERT INTO kurssi (nimi) VALUES (?)");
			kurssiInsert.setString(1, uusiKurssi.getNimi());
			kurssiInsert.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	public static boolean lisaaTunti(Tunti uusiTunti) {
		Connection con = connect();
		try {
			PreparedStatement tuntiInsert = con.prepareStatement("INSERT INTO tunti (kurssi, viikonpaiva, alkuklo, loppuklo) VALUES ?,?,?,?");
			tuntiInsert.setInt(1, uusiTunti.getKurssi().getId());
			tuntiInsert.setString(2, uusiTunti.getViikonpaiva());
			tuntiInsert.setInt(3, uusiTunti.getAlkuklo());
			tuntiInsert.setInt(4, uusiTunti.getLoppuklo());
			tuntiInsert.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	public static boolean lisaaOsallistuminen(Ryhma ryhma, Tunti tunti) {
		Connection con = connect();
		try {
			PreparedStatement osallistumisInsert = con.prepareStatement("INSERT INTO osallistuminen (tunti, ryhma) VALUES ?,?");
			osallistumisInsert.setInt(1, tunti.getId());
			osallistumisInsert.setInt(2, ryhma.getId());
			osallistumisInsert.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	public static boolean poistaRyhma(Ryhma poistettavaRyhma) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM ryhma WHERE ryhmaID=?");
			ps.setInt(1, poistettavaRyhma.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	public static boolean poistaTunti(Tunti poistettavaTunti) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM tunti WHERE tuntiID=?");
			ps.setInt(1, poistettavaTunti.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	public static boolean poistaOsallistuminen(int poistettavaOsallistuminen) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM osallistuminen WHERE ryhma=?");
			ps.setInt(1, poistettavaOsallistuminen);
			ps.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	public static boolean poistaKayttajatunnus(Oppilas poistettavaOppilas) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM oppilas WHERE oppilasID=?");
			ps.setInt(1, poistettavaOppilas.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	public static boolean poistaKayttajatunnus(int tunnus) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM kayttajatunnus WHERE kayttajatunnusID=?");
			ps.setInt(1, tunnus);
			ps.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Luo Ryhma objektin annetun ResultSetin sisällöstä.
	 * 
	 * @param rs
	 * @return ResultSetin sisällöstä muodostettu Oppilas objekti
	 * @throws SQLException
	 */
	private static Ryhma ryhmaResultSetista(ResultSet rs) throws SQLException {
		String ryhmanNimi = rs.getString("nimi");
		int ryhmanId = rs.getInt("ryhmaID");
		Ryhma ryhma = new Ryhma(ryhmanNimi, ryhmanId);
		return ryhma;
	}

	/**
	 * Luo Oppilas objektin annetun ResultSetin sisällöstä.
	 * 
	 * @param rs
	 * @return ResultSetin sisällöstä muodostettu Oppilas objekti
	 * @throws SQLException
	 */
	private static Oppilas oppilasResultSetista(ResultSet rs) throws SQLException {
		String etunimi = rs.getString("etunimi");
		String sukunimi = rs.getString("sukunimi");
		int oppilaanId = rs.getInt("oppilasID");
		Ryhma ryhma = ryhmaResultSetista(rs);
		return new Oppilas(etunimi, sukunimi, ryhma, oppilaanId);
	}

	/**
	 * Luo Kurssi objektin annetun ResultSetin sisällöstä.
	 * 
	 * @param rs
	 * @return ResultSetin sisällöstä muodostetu Oppilas objekti
	 * @throws SQLException
	 */
	private static Kurssi kurssiResultSetista(ResultSet rs) throws SQLException {
		String kurssinNimi = rs.getString("nimi");
		int kurssinId = rs.getInt("kurssiID");
		return new Kurssi(kurssinNimi, kurssinId);
	}

	/**
	 * Luo Tunti objektin annetun ResultSetin sisällöstä.
	 * 
	 * @param rs
	 * @return ResultSetin sisällästä muodostetu Tunti objekti
	 * @throws SQLException
	 */
	private static Tunti tuntiResultSetista(ResultSet rs) throws SQLException {
		Kurssi kurssi = kurssiResultSetista(rs);
		String viikonpaiva = rs.getString("viikonpaiva");
		int alkuklo = rs.getInt("alkuklo");
		int loppuklo = rs.getInt("loppuklo");
		int tunninId = rs.getInt("tuntiID");
		return new Tunti(viikonpaiva, alkuklo, loppuklo, kurssi, tunninId);
	}

	public static List<Oppilas> getOppilaat() {
		Connection c = connect();
		if (c == null) return null;
		try {
			List<Oppilas> oppilaat = new ArrayList<Oppilas>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM oppilas " + "JOIN ryhma ON oppilas.ryhma = ryhma.ryhmaID;");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				oppilaat.add(oppilasResultSetista(rs));
			}

			return oppilaat;
		} catch (SQLException e) {
			return null;
		} finally {
			closeConnection(c);
		}
	}

	public static List<Oppilas> haeOppilaatByName(String etunimi, String sukunimi) {
		Connection c = connect();
		if (c == null) return null;
		try {
			List<Oppilas> oppilaat = new ArrayList<Oppilas>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM oppilas " + "JOIN ryhma ON oppilas.ryhma = ryhma.ryhmaID " + "WHERE etunimi LIKE ? AND sukunimi LIKE ?");
			ps.setString(1, "%" + etunimi + "%");
			ps.setString(2, "%" + sukunimi + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				oppilaat.add(oppilasResultSetista(rs));
			}

			return oppilaat;
		} catch (SQLException e) {
			return null;
		} finally {
			closeConnection(c);
		}
	}
	
	public static Oppilas getKayttajanOppilas(int kayttajatunnusID) {
		Connection c = connect();
		if (c == null)
			return null;
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM oppilas JOIN ryhma ON oppilas.ryhma = ryhma.ryhmaID WHERE oppilas.kayttajatunnus = ?;");
			ps.setInt(1, kayttajatunnusID);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return oppilasResultSetista(rs);
			}

			return null;
		} catch (SQLException e) {
			return null;
		} finally {
			closeConnection(c);
		}
	}
	
	/**
	 * Luo Kayttajatunnus objektin annetun ResultSetin sisällöstä.
	 * 
	 * @param rs
	 * @return ResultSetin sisällöstä muodostetu Kayttajatunnus objekti
	 * @throws SQLException
	 */
	private static Kayttajatunnus kayttajatunnusResultSetista(ResultSet rs) throws SQLException {
		String kayttajanimi = rs.getString("kayttajanimi");
		String salasana = rs.getString("salasana");
		int oikeudet = rs.getInt("oikeudet");
		int id = rs.getInt("kayttajatunnusID");
		Oppilas oppilas;
		try {
			oppilas = oppilasResultSetista(rs);
		} catch (SQLException e) {
			oppilas = getKayttajanOppilas(id);
		}
		return new Kayttajatunnus(kayttajanimi, salasana, oikeudet, oppilas, id);
	}
	
	public static List<Kayttajatunnus> haeKayttajatunnukset(String kayttajanimi) {
		Connection c = connect();
		if (c == null)
			return null;
		try {
			List<Kayttajatunnus> oppilaat = new ArrayList<Kayttajatunnus>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM kayttajatunnus WHERE kayttajanimi LIKE ?");
			ps.setString(1, "%" + kayttajanimi + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				oppilaat.add(kayttajatunnusResultSetista(rs));
			}

			return oppilaat;
		} catch (SQLException e) {
			return null;
		} finally {
			closeConnection(c);
		}
	}
	
	public static List<Kayttajatunnus> haeKayttajatunnukset(String kayttajanimi, String etunimi, String sukunimi) {
		Connection c = connect();
		if (c == null)
			return null;
		try {
			List<Kayttajatunnus> oppilaat = new ArrayList<Kayttajatunnus>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM kayttajatunnus JOIN oppilas ON kayttajatunnus = kayttajatunnusID WHERE kayttajanimi LIKE ? AND etunimi LIKE ? AND sukunimi LIKE ?");
			ps.setString(1, "%" + kayttajanimi + "%");
			ps.setString(2, "%" + etunimi + "%");
			ps.setString(3, "%" + sukunimi + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				oppilaat.add(kayttajatunnusResultSetista(rs));
			}

			return oppilaat;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeConnection(c);
		}
	}

	public static List<Kurssi> getKurssit() {
		Connection c = connect();
		if (c == null) return null;
		try {
			List<Kurssi> kurssit = new ArrayList<Kurssi>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM kurssi;");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				kurssit.add(kurssiResultSetista(rs));
			}

			return kurssit;
		} catch (SQLException e) {
			return null;
		} finally {
			closeConnection(c);
		}
	}

	public static List<Ryhma> getRyhmat() {
		Connection c = connect();
		if (c == null) return null;
		try {
			List<Ryhma> ryhmat = new ArrayList<Ryhma>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM ryhma;");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ryhmat.add(ryhmaResultSetista(rs));
			}

			return ryhmat;
		} catch (SQLException e) {
			return null;
		} finally {
			closeConnection(c);
		}
	}

	public static List<Tunti> getRyhmanTunnit(Ryhma ryhma) {
		Connection c = connect();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM ryhma JOIN tunti ON tunti.ryhma = ryhma.ryhmaID JOIN kurssi ON tunti.kurssi = kurssi.kurssiID WHERE ryhma.ryhmaID = ?");
			ps.setInt(1, ryhma.getId());
			List<Tunti> tunnit = new ArrayList<Tunti>();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tunnit.add(tuntiResultSetista(rs));
			}

			return tunnit;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			closeConnection(c);
		}
	}

	
	public static List<Ryhma> haeRyhmat(String nimi) {
		Connection c = connect();
		if (c == null)
			return null;
		try {
			List<Ryhma> ryhmat = new ArrayList<Ryhma>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM ryhma WHERE nimi LIKE ?;");
			ps.setString(1, "%" + nimi + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ryhmat.add(ryhmaResultSetista(rs));
			}

			return ryhmat;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeConnection(c);
		}
	}

	/**
	 * Päivittää annetun oppilaan tiedot.
	 * 
	 * @param oppilas
	 *            päivitetty oppilas.
	 * @return true jos päivittäminen onnistui.
	 */
	public static boolean updateOppilas(Oppilas oppilas) {
		Connection con = connect();
		try {
			PreparedStatement updateOppilas = con.prepareStatement("UPDATE oppilas SET etunimi=?, sukunimi=?, ryhma=? WHERE oppilasID=?");
			updateOppilas.setString(1, oppilas.getEtunimi());
			updateOppilas.setString(2, oppilas.getSukunimi());
			updateOppilas.setInt(3, oppilas.getRyhma().getId());
			updateOppilas.setInt(4, oppilas.getId());

			updateOppilas.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Päivittää annetun käyttäjätunnuksen tiedot.
	 * 
	 * @param kayttaja
	 *            päivitetty käyttäjätunnus.
	 * @return ture jos päivittäminen onnistui.
	 */
	public static boolean updateKayttajatunnus(Kayttajatunnus kayttaja) {
		Connection con = connect();
		try {
			con.setAutoCommit(false);
			PreparedStatement updateKayttajatunnus = con.prepareStatement("UPDATE kayttajatunnus SET kayttajanimi = ?, salasana = ?, oikeudet = ? WHERE kayttajatunnusID = ?");

			updateKayttajatunnus.setString(1, kayttaja.getKayttajanimi());
			updateKayttajatunnus.setString(2, kayttaja.getSalasana());
			updateKayttajatunnus.setInt(3, kayttaja.getOikeudet());
			updateKayttajatunnus.setInt(4, kayttaja.getId());

			updateKayttajatunnus.executeUpdate();

			if (kayttaja.getOppilas() != null) {
				Oppilas oppilas = kayttaja.getOppilas();
				PreparedStatement updateOppilas = con.prepareStatement("UPDATE oppilas SET etunimi = ?, sukunimi = ?, ryhma = ? WHERE oppilasID = ?;");

				updateOppilas.setString(1, oppilas.getEtunimi());
				updateOppilas.setString(2, oppilas.getSukunimi());
				updateOppilas.setInt(3, oppilas.getRyhma().getId());
				updateOppilas.setInt(4, oppilas.getId());

				updateOppilas.executeUpdate();
			}

			con.commit();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			try {
				con.rollback();
			} catch (Exception e) {
			}
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Päivittää annetun kurssin tiedot.
	 * 
	 * @param kurssi
	 *            päivitetty kurssi
	 * @return true jos ryhmän päivittäminen onnistui
	 */
	public static boolean updateKurssi(Kurssi kurssi) {
		Connection con = connect();
		try {
			PreparedStatement updateKurssi = con.prepareStatement("UPDATE kurssi SET nimi=? WHERE kurssiID=?");
			updateKurssi.setString(1, kurssi.getNimi());
			updateKurssi.setInt(2, kurssi.getId());

			updateKurssi.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Päivittää annetun ryhmän tiedot.
	 * 
	 * @param ryhma
	 *            päivitetty ryhmä
	 * @return true jos ryhmän päivittäminen onnistui
	 */
	public static boolean updateRyhma(Ryhma ryhma) {
		Connection con = connect();
		try {
			PreparedStatement updateRyhma = con.prepareStatement("UPDATE ryhma SET nimi=? WHERE ryhmaID=?");
			updateRyhma.setString(1, ryhma.getNimi());
			updateRyhma.setInt(2, ryhma.getId());

			updateRyhma.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Päivittää annetun tunnin tiedot
	 * 
	 * @param tunti
	 *            päivitettävä tunti
	 * @return true jos päivitys onnistui
	 */
	public static boolean updateTunti(Tunti tunti) {
		Connection con = connect();
		try {
			PreparedStatement updateTunti = con.prepareStatement("UPDATE tunti SET viikonpaiva=?, alkuklo=?, loppuklo=? WHERE tuntiID=?");
			updateTunti.setString(1, tunti.getViikonpaiva());
			updateTunti.setInt(2, tunti.getAlkuklo());
			updateTunti.setInt(3, tunti.getLoppuklo());
			updateTunti.setInt(4, tunti.getId());

			updateTunti.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection(con);
		}
	}
}
