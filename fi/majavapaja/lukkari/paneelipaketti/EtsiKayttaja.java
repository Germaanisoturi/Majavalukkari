package fi.majavapaja.lukkari.paneelipaketti;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kayttajatunnus;
import fi.majavapaja.lukkari.Oppilas;
import fi.majavapaja.lukkari.Paaikkuna;

/**
 * EtsiKayttaja paneelissa etsitään oppilas nimen ja käyttäjätunnuksen
 * avulla muokkaamista varten.
 *
 * @author Majavapaja
 */
@SuppressWarnings("serial")
public class EtsiKayttaja extends JPanel {
	private JTextField etunimiField;
	private JTextField sukunimiField;
	private JList<Kayttajatunnus> kayttajatunnuksetList;
	private JTextArea oppilasInfoTextArea;
	private Paaikkuna paaikkuna;
	private JTextField kayttajatunnusField;

	/**
	 * Luo oppilaanhakunäkymän.
	 * 
	 * @param paaikkuna Paaikkuna, johon paneeli sijoitetaan
	 */
	public EtsiKayttaja(Paaikkuna paaikkuna) {
		this.paaikkuna = paaikkuna;
		setSize(800, 600);
		
		KeyAdapter enterAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					etsiActionPerformed();
				}
			}
		};
		
		JLabel lblEtunimi = new JLabel("Etunimi");
		
		etunimiField = new JTextField();
		lblEtunimi.setLabelFor(etunimiField);
		etunimiField.setColumns(10);
		etunimiField.addKeyListener(enterAdapter);
		
		JLabel lblSukunimi = new JLabel("Sukunimi");
		
		sukunimiField = new JTextField();
		lblSukunimi.setLabelFor(sukunimiField);
		sukunimiField.setColumns(10);
		sukunimiField.addKeyListener(enterAdapter);
		
		JButton btnEtsi = new JButton("Etsi");
		btnEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etsiActionPerformed();
			}
		});
		
		JButton btnTakaisin = new JButton("Takaisin");
		btnTakaisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takaisinActionPerformed();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		oppilasInfoTextArea = new JTextArea();
		oppilasInfoTextArea.setLineWrap(true);
		oppilasInfoTextArea.setEditable(false);
		
		JButton btnNewButton = new JButton("Muokkaa tietoja");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muokkaaOppilastaActionPerformed();
			}
		});
		
		JLabel lblKayttajatunnus = new JLabel("Käyttäjätunnus");
		
		kayttajatunnusField = new JTextField();
		lblKayttajatunnus.setLabelFor(kayttajatunnusField);
		kayttajatunnusField.setColumns(10);
		kayttajatunnusField.addKeyListener(enterAdapter);
		
		JButton btnPoistaOppilas = new JButton("Poista oppilas");
		btnPoistaOppilas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				poistaActionPerformed();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addComponent(btnEtsi, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnTakaisin, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPoistaOppilas, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
								.addComponent(oppilasInfoTextArea)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblEtunimi, Alignment.LEADING)
									.addComponent(lblSukunimi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblKayttajatunnus))
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(etunimiField, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
								.addComponent(sukunimiField, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
								.addComponent(kayttajatunnusField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEtunimi)
						.addComponent(etunimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSukunimi)
						.addComponent(sukunimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKayttajatunnus)
						.addComponent(kayttajatunnusField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEtsi)
						.addComponent(btnTakaisin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(oppilasInfoTextArea, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnPoistaOppilas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		kayttajatunnuksetList = new JList<Kayttajatunnus>();
		kayttajatunnuksetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		kayttajatunnuksetList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				oppilaatListSelectionChanged(e);
			}
		});
		scrollPane.setViewportView(kayttajatunnuksetList);
		setLayout(groupLayout);
	}

	/**
	 * Poistaa valitun oppilaan tietokannasta ja listasta.
	 */
	private void poistaActionPerformed() {
		int i = kayttajatunnuksetList.getSelectedIndex();
		if (i == -1)
			return;
		
		Kayttajatunnus kt = (Kayttajatunnus) kayttajatunnuksetList.getModel().getElementAt(i);
		boolean onnistui = Database.poistaKayttajatunnus(kt);
		if (!onnistui) {
			JOptionPane.showMessageDialog(this, "Käyttäjätunnuksen poistaminen epäonnistui", "Virhe", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		etsiActionPerformed();
	}

	/**
	 * Hakee tietokannasta syötettyjä tietoja vastaavat oppilaat ja
	 * lisää ne hakutulosten listaan.
	 */
	private void etsiActionPerformed() {
		String kayttajanimi = kayttajatunnusField.getText().trim();
		String etunimi = etunimiField.getText().trim();
		String sukunimi = sukunimiField.getText().trim();
		
		List<Kayttajatunnus> kayttajatunnukset;
		
		if ("".equals(etunimi) && "".equals(sukunimi)) {
			kayttajatunnukset = Database.haeKayttajatunnukset(kayttajanimi);
		} else {
			kayttajatunnukset = Database.haeKayttajatunnukset(kayttajanimi, etunimi, sukunimi);
		}
		
		if (kayttajatunnukset == null) {
			JOptionPane.showMessageDialog(this, "Virhe haettaessa käyttäjiä tietokannasta", "Virhe", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		kayttajatunnuksetList.setListData(kayttajatunnukset.toArray(new Kayttajatunnus[0]));
	}
	
	/**
	 * Näyttää oppilaan tiedot tekstikentässä, kun oppilas valitaan
	 * hakutulosten listasta.
	 */
	private void oppilaatListSelectionChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		int i = kayttajatunnuksetList.getSelectedIndex();
		if (i == -1) {
			// Listassa ei ole enää valintaa, tyhjennä infoteksti
			oppilasInfoTextArea.setText("");
			return;
		}
		
		Kayttajatunnus kt = (Kayttajatunnus) kayttajatunnuksetList.getModel().getElementAt(i);
		String infoteksti = "";
		if (kt.getOppilas() == null) {
			// Ylläpitäjä
			infoteksti = "Käyttäjänimi:\t" + kt.getKayttajanimi();
			infoteksti += "\n\nKäyttäjä on ylläpitäjä.";
		} else {
			// Oppilas
			Oppilas o = kt.getOppilas();
			infoteksti = "Käyttäjänimi:\t" + kt.getKayttajanimi();
			infoteksti += "\nNimi:\t" + o.getEtunimi() + " " + o.getSukunimi();
			infoteksti += "\nRyhmä:\t" + o.getRyhma().getNimi();
		}
		oppilasInfoTextArea.setText(infoteksti);
		
	}
	
	/**
	 * Pyytää pääikkunaa palaamaan edelliseen paneeliin.
	 */
	private void takaisinActionPerformed() {
		paaikkuna.edellinenPaneeli();
	}
	
	/**
	 * Vaihtaa näkymäksi uuden MuokkaaKayttajaa paneelin, jos
	 * hakutuloksista on valittuna oppilas.
	 */
	private void muokkaaOppilastaActionPerformed() {
		int i = kayttajatunnuksetList.getSelectedIndex();
		if (i == -1)
			return;
		Kayttajatunnus oppilas = (Kayttajatunnus) kayttajatunnuksetList.getModel().getElementAt(i);
		paaikkuna.vaihdaPaneeli(new MuokkaaKayttajaa(paaikkuna, oppilas));
	}
}
