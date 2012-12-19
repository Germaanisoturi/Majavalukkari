package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import fi.majavapaja.lukkari.*;

/**
 * Lukujärjestyksen näyttämiseen tenhty paneeli.
 * 
 * @author s1001086
 * 
 */
@SuppressWarnings("serial")
public class MuokkaaLukkaria extends JPanel implements MouseListener {
	// PERSE

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
	private List<JLabel> valikoidutTunnit = new ArrayList<JLabel>();
	private Ryhma ryhma;
	private static final Kurssi poistaTunti = new Kurssi("*** Poista Tunti ***", -9001);

	/**
	 * Tekee uuden lukkarin paneelin.
	 * 
	 * @param ryhma
	 *            ryhmä jonka lukujärjestys ladataan.
	 */
	public MuokkaaLukkaria(Ryhma ryhma) {
		this.ryhma = ryhma;
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout());

		sisaltoPaneeli = new JPanel(new GridLayout(RIVI, SARA));
		vkpaivatPaneeli = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		kellonajatPaneeli = new JPanel(new GridLayout(0, 1));

		tunnit = Database.getRyhmanTunnit(ryhma);
		taulukonAlustus();

		add(sisaltoPaneeli, BorderLayout.CENTER);
		add(vkpaivatPaneeli, BorderLayout.NORTH);
		add(kellonajatPaneeli, BorderLayout.WEST);

		// ALL OUR PURPLE FRIENDS throw throws synchronized try catch return null true false else super finally this instanceof assert do while break continue if public static final abstract void int char boolean double float enum long short byte interface class extends implements new private protected case switch for import package
	}

	private void taulukonAlustus() {
		if (vkpaivatPaneeli != null) vkpaivatPaneeli.removeAll();
		if (kellonajatPaneeli != null) kellonajatPaneeli.removeAll();
		if (sisaltoPaneeli != null) sisaltoPaneeli.removeAll();

		vkpaivat = new JLabel[SARA];
		kellonajat = new JLabel[RIVI];

		Border reunat = BorderFactory.createLineBorder(Color.BLACK);

		for (int i = 0; i < vkpaivat.length; i++) {
			vkpaivat[i] = new JLabel(vkpaivatTxt[i]);
			vkpaivat[i].setName(vkpaivat[i].getText().substring(0, 2));
			vkpaivat[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			vkpaivat[i].setHorizontalAlignment(JLabel.CENTER);
			vkpaivat[i].setBorder(reunat);
			vkpaivat[i].setOpaque(true);
			vkpaivat[i].setBackground(Color.WHITE);
		}

		for (int i = 0; i < kellonajat.length; i++) {
			kellonajat[i] = new JLabel(kellonajatTxt[i]);
			kellonajat[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			kellonajat[i].setHorizontalAlignment(JLabel.CENTER);
			kellonajat[i].setBorder(reunat);
			kellonajat[i].setPreferredSize(new Dimension(100, 0));
			kellonajat[i].setOpaque(true);
			kellonajat[i].setBackground(Color.WHITE);
		}

		sisalto = new JLabel[RIVI][SARA - 1];

		for (int i = 0; i < sisalto.length; i++) {
			for (int j = 0; j < sisalto[i].length; j++) {
				if (sisalto[i][j] == null) {
					sisalto[i][j] = new JLabel() {
						@Override
						protected void paintComponent(Graphics g) {
							g.clearRect(0, 0, getWidth(), getHeight());
							super.paintComponent(g);
						}
					};
					sisalto[i][j].setHorizontalAlignment(JLabel.CENTER);
					sisalto[i][j].setBorder(reunat);
					sisalto[i][j].setOpaque(true);
					sisalto[i][j].setBackground(Color.WHITE);
					sisalto[i][j].addMouseListener(this);
					sisalto[i][j].setName(i + "#" + j);
				}
			}
		}

		for (int i = 0; i < tunnit.size(); i++) {
			for (int paiva = 0; paiva < vkpaivat.length; paiva++) {
				if (tunnit.get(i).getViikonpaiva().equalsIgnoreCase(vkpaivat[paiva].getName())) {
					int alkuklo = tunnit.get(i).getAlkuklo() - 8;
					int loppuklo = tunnit.get(i).getLoppuklo() - 8;

					for (int klo = alkuklo; klo <= loppuklo; klo++) {
						sisalto[klo][paiva - 1].setText(tunnit.get(i).getKurssi().getNimi());

						if (alkuklo == loppuklo) {
							sisalto[klo][paiva - 1].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
						} else if (klo == alkuklo) {
							sisalto[klo][paiva - 1].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
						} else if (klo == loppuklo) {
							sisalto[klo][paiva - 1].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
						} else {
							sisalto[klo][paiva - 1].setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
						}
					}
				}
			}
		}

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

		for (int i = 0; i < vkpaivat.length; i++) {
			if (i == 0) {
				vkpaivat[i].setPreferredSize(new Dimension(100, 20));
			} else {
				vkpaivat[i].setPreferredSize(new Dimension(140, 20));
			}
		}
	}

	private boolean valitseTunti(JLabel source) {
		for (int i = 0; i < valikoidutTunnit.size(); i++) {
			if (!valikoidutTunnit.get(i).getName().split("#")[1].equalsIgnoreCase(source.getName().split("#")[1])) {
				tyhjennaValikoidutTunnit();
				source.setBackground(new Color(0, 0, 255, 100));
				valikoidutTunnit.add(source);
				return true;
			}
		}
		for (int i = 0; i < valikoidutTunnit.size(); i++) {
			if (valikoidutTunnit.get(i) == source) {
				source.setBackground(Color.WHITE);
				valikoidutTunnit.remove(source);
				return false;
			}
		}
		source.setBackground(new Color(0, 0, 255, 100));
		valikoidutTunnit.add(source);
		return true;
	}

	private void tyhjennaValikoidutTunnit() {
		for (int j = 0; j < valikoidutTunnit.size(); j++) {
			valikoidutTunnit.get(j).setBackground(Color.WHITE);
		}
		valikoidutTunnit.clear();
	}

	private void tiivistaTunnit(JLabel source) {
		int[] ajat = new int[valikoidutTunnit.size()];

		int vkpaiva = Integer.parseInt(valikoidutTunnit.get(0).getName().split("#")[1]);
		int aloitusAika = 8;
		int lopetusAika = -1;

		for (int i = 0; i < valikoidutTunnit.size(); i++) {
			ajat[i] = Integer.parseInt(valikoidutTunnit.get(i).getName().split("#")[0]);
			if (ajat[i] < aloitusAika) aloitusAika = ajat[i];
			if (ajat[i] > lopetusAika) lopetusAika = ajat[i];
		}

		for (int klo = aloitusAika; klo < lopetusAika; klo++) {
			if (klo > aloitusAika && klo < lopetusAika) {
				if (source == sisalto[klo][vkpaiva]) {
					tyhjennaValikoidutTunnit();
					return;
				}
				boolean loytyy = false;
				for (int i = 0; i < valikoidutTunnit.size(); i++) {
					if (valikoidutTunnit.get(i) == sisalto[klo][vkpaiva]) {
						loytyy = true;
						break;
					}
				}
				if (!loytyy) valitseTunti(sisalto[klo][vkpaiva]);
			}
		}
	}

	private void luoTunti() {
		int[] ajat = new int[valikoidutTunnit.size()];
		for (int i = 0; i < valikoidutTunnit.size(); i++) {
			ajat[i] = Integer.parseInt(valikoidutTunnit.get(i).getName().split("#")[0]);
		}

		int aloitusAika = 16;
		int lopetusAika = 0;

		for (int i = 0; i < ajat.length; i++) {
			if (ajat[i] < aloitusAika) aloitusAika = ajat[i];
			if (ajat[i] > lopetusAika) lopetusAika = ajat[i];
		}

		int vkpaiva = Integer.parseInt(valikoidutTunnit.get(0).getName().split("#")[1]);
		String valittuViikonpaiva = vkpaivatTxt[vkpaiva + 1].substring(0, 2);

		aloitusAika = Integer.parseInt(kellonajatTxt[aloitusAika]);
		lopetusAika = Integer.parseInt(kellonajatTxt[lopetusAika]);

		Tunti poistettavaTunti = null;

		for (int i = 0; i < tunnit.size(); i++) {
			if (tunnit.get(i).getViikonpaiva().equalsIgnoreCase(valittuViikonpaiva)) {
				if (tunnit.get(i).getAlkuklo() == aloitusAika && tunnit.get(i).getLoppuklo() == lopetusAika) {
					poistettavaTunti = tunnit.get(i);
					break;
				}
			}
		}

		List<Kurssi> k = Database.getKurssit();
		if (poistettavaTunti != null) k.add(0, poistaTunti);

		JComboBox<Kurssi> compo = new JComboBox<Kurssi>(k.toArray(new Kurssi[k.size()]));
		int vastaus = JOptionPane.showConfirmDialog(null, compo, "Valitse kurssi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (vastaus == JOptionPane.OK_OPTION) {
			Kurssi kurssi = (Kurssi) compo.getSelectedItem();
			Tunti tunti = new Tunti(valittuViikonpaiva, aloitusAika, lopetusAika, kurssi, ryhma);

			if (kurssi == poistaTunti) {
				int input = JOptionPane.showConfirmDialog(this, "Oletko varma että haluat poistaa tämän tunnin?", "Varmista poisto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (input == JOptionPane.OK_OPTION) {
					Database.poistaTunti(poistettavaTunti);
				}
			} else {
				for (int i = 0; i < tunnit.size(); i++) {
					if (tunnit.get(i).getViikonpaiva().equalsIgnoreCase(tunti.getViikonpaiva())) {
						for (int klo = tunnit.get(i).getAlkuklo(); klo <= tunnit.get(i).getLoppuklo(); klo++) {
							if (klo >= aloitusAika && klo <= lopetusAika) {
								Database.poistaTunti(tunnit.get(i));
							}
						}
					}
				}
				Database.lisaaTunti(tunti);
			}
			tunnit = Database.getRyhmanTunnit(ryhma);
			taulukonAlustus();
			this.revalidate();
			valikoidutTunnit.clear();
		}
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (MouseEvent.BUTTON1 == e.getButton()) {
			JLabel source = (JLabel) e.getSource();
			valitseTunti(source);
			if (!valikoidutTunnit.isEmpty()) tiivistaTunnit(source);
		} else if (MouseEvent.BUTTON3 == e.getButton()) {
			if (!valikoidutTunnit.isEmpty()) {
				luoTunti();
			}
		}
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * Invoked when the mouse enters a component.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Invoked when the mouse exits a component.
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Invoked when the mouse button has been clicked (pressed and released) on a component.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}
}