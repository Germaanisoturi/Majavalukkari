package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import com.sun.org.apache.bcel.internal.generic.Select;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Ryhma;
import fi.majavapaja.lukkari.Tunti;

public class AlmightyLukkariPaneeli extends JPanel implements MouseListener {
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
		 * ALL OUR PURPLE FRIENDS do while break continue if public static final abstract void int char boolean double float enum long short byte interface class extends implements new private protected case switch for import package
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
		Border otsikkoReunat = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK);

		for (int i = 0; i < vkpaivat.length; i++) {
			vkpaivat[i] = new JLabel(vkpaivatTxt[i]);
			vkpaivat[i].setName(vkpaivat[i].getText().substring(0, 2));
			vkpaivat[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			vkpaivat[i].setHorizontalAlignment(JLabel.CENTER);
			vkpaivat[i].setBorder(otsikkoReunat);
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
					sisalto[i][j].setName("" + j);
				}
			}
		}

		for (int i = 0; i < tunnit.size(); i++) {
			for (int paiva = 0; paiva < vkpaivat.length; paiva++) {
				if (tunnit.get(i).getViikonpaiva().equalsIgnoreCase(vkpaivat[paiva].getName())) {
					int alkuklo = tunnit.get(i).getAlkuklo();
					int loppuklo = tunnit.get(i).getLoppuklo();
					for (int klo = alkuklo; klo < loppuklo; klo++) {
						sisalto[klo - 8][paiva - 1].setText(tunnit.get(i).getKurssi().getNimi());
						if (klo + 1 < tunnit.get(i).getLoppuklo()) {
							sisalto[klo - 8][paiva - 1].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
						} else if (klo + 1 == loppuklo && alkuklo + 1 < loppuklo) {
							sisalto[klo - 8][paiva - 1].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
						}
					}
				}
			}
		}
	}

	private void selectTunti(JLabel source) {
		for (int i = 0; i < valikoidutTunnit.size(); i++) {
			if (!valikoidutTunnit.get(i).getName().equalsIgnoreCase(source.getName())) {
				for (int j = 0; j < valikoidutTunnit.size(); j++) {
					valikoidutTunnit.get(j).setBackground(Color.WHITE);
				}
				valikoidutTunnit.clear();
				source.setBackground(new Color(0,0,255,100));
				valikoidutTunnit.add(source);
				return;
			}
		}
		for (int i = 0; i < valikoidutTunnit.size(); i++) {
			if (valikoidutTunnit.get(i) == source) {
				source.setBackground(Color.WHITE);
				valikoidutTunnit.remove(source);
				return;
			}
		}
		source.setBackground(new Color(0,0,255,100));
		valikoidutTunnit.add(source);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	private boolean mousePress;
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (MouseEvent.BUTTON1 == e.getButton()) {
			JLabel source = (JLabel) e.getSource();
			selectTunti(source);
			source.repaint();
			mousePress = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(MouseEvent.BUTTON1 == e.getButton()) mousePress = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {}

	public static void main(String[] args) {
		AlmightyLukkariPaneeli lukkari = new AlmightyLukkariPaneeli(Database.getRyhmat().get(2));
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(lukkari);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}