package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kurssi;
import fi.majavapaja.lukkari.Paaikkuna;

/**
 * EtsiKurssi paneelissa etsitään ryhmän kurssi muokkaamista varten.
 *
 * @author Majavapaja
 */
@SuppressWarnings("serial")
public class EtsiKurssi extends JPanel {
	private JTextField kurssinNimiField;
	private Paaikkuna ikkuna;
	private JList<Kurssi> kurssiList;
	
	/**
	 * Luo kurssinhakunäkymän.
	 * 
	 * @param ikkuna Paaikkuna, johon paneeli sijoitetaan.
	 */
	public EtsiKurssi(Paaikkuna ikkuna) {
		this.ikkuna = ikkuna;
		setPreferredSize(new Dimension(800, 600));
		
		KeyAdapter enterAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					haeActionPerformed();
				}
			}
		};
		
		
		JLabel lblKurssinNimi = new JLabel("Kurssin nimi");
		
		kurssinNimiField = new JTextField();
		lblKurssinNimi.setLabelFor(kurssinNimiField);
		kurssinNimiField.setColumns(10);
		kurssinNimiField.addKeyListener(enterAdapter);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnHae = new JButton("Hae");
		btnHae.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				haeActionPerformed();
			}
		});
		
		JButton btnTakaisin = new JButton("Takaisin");
		btnTakaisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takaisinActionPerformed();
			}
		});
		
		JButton btnMuokkaaKurssia = new JButton("Muokkaa kurssia");
		btnMuokkaaKurssia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muokkaaActionPerformed();
			}
		});
		
		JButton btnPoistaKurssi = new JButton("Poista kurssi");
		btnPoistaKurssi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				poistaActionPerformed();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKurssinNimi)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(kurssinNimiField, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(622, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnHae, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPoistaKurssi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addComponent(btnMuokkaaKurssia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
							.addGap(622))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKurssinNimi)
						.addComponent(kurssinNimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnHae)
						.addComponent(btnTakaisin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMuokkaaKurssia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPoistaKurssi)
					.addContainerGap(231, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnHae, btnTakaisin});
		
		kurssiList = new JList<Kurssi>();
		scrollPane.setViewportView(kurssiList);
		setLayout(groupLayout);
	}
	
	/**
	 * Poistaa valitun oppilaan tietokannasta ja listasta.
	 */
	protected void poistaActionPerformed() {
		int i = kurssiList.getSelectedIndex();
		if (i == -1)
			return;
		
		Kurssi k = (Kurssi) kurssiList.getModel().getElementAt(i);
		boolean onnistui = Database.poistaKurssi(k);
		if (!onnistui) {
			JOptionPane.showMessageDialog(this, "Kurssin poistaminen epäonnistu. Onko ryhmillä kyseisen kurssin tunteja?", "Virhe", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		haeActionPerformed();
	}

	/**
	 * Vaihtaa näkymäksi uuden MuokkaaKurssia paneelin, jos
	 * hakutuloksista on valittuna ryhmä.
	 */
	protected void muokkaaActionPerformed() {
		int i = kurssiList.getSelectedIndex();
		if (i == -1)
			return;
		Kurssi kurssi = (Kurssi) kurssiList.getModel().getElementAt(i);
		ikkuna.vaihdaPaneeli(new MuokkaaKurssia(ikkuna, kurssi));
	}
	
	/**
	 * Pyytää pääikkunaa palaamaan edelliseen paneeliin.
	 */
	protected void takaisinActionPerformed() {
		ikkuna.edellinenPaneeli();
	}
	
	/**
	 * Hakee tietokannasta syötettyä nimeä vastaavat kurssit ja
	 * lisää ne hakutulosten listaan.
	 */
	protected void haeActionPerformed() {
		String nimi = kurssinNimiField.getText().trim();
		
		List<Kurssi> kurssit = Database.haeKurssit(nimi);
		
		if (kurssit == null) {
			JOptionPane.showMessageDialog(this, "Virhe haettaessa kursseja tietokannasta", "Virhe", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		kurssiList.setListData(kurssit.toArray(new Kurssi[0]));
	}
}
