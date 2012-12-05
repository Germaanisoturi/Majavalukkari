package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Ryhma;
import fi.majavapaja.lukkari.Tunti;

public class AlmightyLukkariPaneeli extends JPanel {

	private static final int RIVI = 9;
	private static final int SARA = 6;

	private String[] vkpaivatTxt = { "KLO", "Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai" };
	private String[] kellonajatTxt = { "8", "9", "10", "11", "12", "13", "14", "15", "16" };

	private JPanel sisaltoPaneeli;
	private JPanel vkpaivatPaneeli;
	private JPanel kellonajatPaneeli;
	private JLabel[][] sisalto;
	private JLabel[] vkpaivat;
	private JLabel[] kellonajat;
	private List<Tunti> tunnit;

	public AlmightyLukkariPaneeli(Ryhma ryhma) {
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout());

		sisaltoPaneeli = new JPanel(new GridLayout(RIVI, SARA));
		vkpaivatPaneeli = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		kellonajatPaneeli = new JPanel(new GridLayout(0, 1));

		tunnit = Database.getRyhmanTunnit(ryhma);

		taulukonAlustus();

		for (int i = 0; i < sisalto.length; i++) {
			for (int j = 0; j < sisalto[i].length; j++) {
				sisaltoPaneeli.add(sisalto[i][j]);
			}
		}

		for (int i = 0; i < vkpaivat.length; i++) {
			vkpaivatPaneeli.add(vkpaivat[i]);
		}

		for (int i = 0; i < kellonajat.length; i++) {
			kellonajatPaneeli.add(kellonajat[i]);
		}

		add(sisaltoPaneeli, BorderLayout.CENTER);
		add(vkpaivatPaneeli, BorderLayout.NORTH);
		add(kellonajatPaneeli, BorderLayout.WEST);

		/*
		 * do while break continue if public static final abstract void int char boolean double float enum long short byte interface class extends implements new private protected case switch for import package
		 */

		for (int i = 0; i < vkpaivat.length; i++) {
			if (i == 0) {
				vkpaivat[i].setPreferredSize(new Dimension(100, 20));
			} else {
				vkpaivat[i].setPreferredSize(new Dimension(140, 20));
			}
		}
	}

	private void taulukonAlustus() {
		vkpaivat = new JLabel[SARA];
		kellonajat = new JLabel[RIVI];

		Border reunat = BorderFactory.createLineBorder(Color.BLACK);
		Border otsikkoReunat = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK);

		for (int i = 0; i < vkpaivat.length; i++) {
			vkpaivat[i] = new JLabel(vkpaivatTxt[i]);
			vkpaivat[i].setName(vkpaivat[i].getText().substring(0, 2));
			vkpaivat[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			vkpaivat[i].setHorizontalAlignment(JLabel.CENTER);
			vkpaivat[i].setBorder(otsikkoReunat);
		}

		for (int i = 0; i < kellonajat.length; i++) {
			kellonajat[i] = new JLabel(kellonajatTxt[i]);
			kellonajat[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			kellonajat[i].setHorizontalAlignment(JLabel.CENTER);
			kellonajat[i].setBorder(reunat);
			kellonajat[i].setPreferredSize(new Dimension(100, 0));
		}

		sisalto = new JLabel[RIVI][SARA - 1];

		for (int i = 0; i < tunnit.size(); i++) {
			for (int j = 0; j < vkpaivatTxt.length; j++) {
				if (tunnit.get(i).getViikonpaiva().equalsIgnoreCase(vkpaivatTxt[i])) {

				}
			}
		}

		for (int i = 0; i < sisalto.length; i++) {
			for (int j = 0; j < sisalto[i].length; j++) {
				if (sisalto[i][j] == null) {
					sisalto[i][j] = new JLabel();
					sisalto[i][j].setBorder(reunat);
				}
			}
		}
	}

	public static void main(String[] args) {
		AlmightyLukkariPaneeli lukkari = new AlmightyLukkariPaneeli(new Ryhma("ABC"));
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(lukkari);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
