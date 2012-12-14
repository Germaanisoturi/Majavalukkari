package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kayttajatunnus;
import fi.majavapaja.lukkari.Oppilas;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

/**
 * @author Majavapaja
 */
@SuppressWarnings("serial")
public class LisaaKayttaja extends JPanel implements ActionListener {
	private JLabel lblKayttajatunnus = new JLabel("Käyttäjätunnus:");
	private JLabel lblSalasana = new JLabel("Salasana:");
	private JLabel lblSalasanaUudestaan = new JLabel("Uudestaan:");
	private JLabel lblOikeudet = new JLabel("Oikeudet:");
	private JLabel lblEtunimi = new JLabel("Etunimi:");
	private JLabel lblSukunimi = new JLabel("Sukunimi:");
	private JLabel lblRyhm = new JLabel("Ryhmä:");
	private JTextField tfKayttajatunnus;
	private JTextField tfEtunimi;
	private JTextField tfSukunimi;
	private JPasswordField pfSalasana;
	private JPasswordField pfSalasanaUudestaan;
	private JRadioButton rdbtnOppilas = new JRadioButton("Oppilas");
	private JRadioButton rdbtnYllpito = new JRadioButton("Ylläpito");
	private int Oikeudet = 0;
	private Ryhma[] ryhmaData;
	private JComboBox<Ryhma> cbRyhma;
	private JButton btnLis = new JButton("Lisää");
	private JButton btnPalaa = new JButton("Takaisin");
	private Paaikkuna paaikkuna = new Paaikkuna();

	/**
	 * Luo paneelin.
	 * @param paaikkuna
	 */
	public LisaaKayttaja(Paaikkuna paaikkuna) {

		KeyAdapter enterListener =  new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					addUser();
				}
			}
		};
		this.paaikkuna = paaikkuna;
		
		setSize(800, 600);

		tfKayttajatunnus = new JTextField();
		tfKayttajatunnus.setColumns(10);
		tfKayttajatunnus.addKeyListener(enterListener);

		ButtonGroup bg = new ButtonGroup();


		rdbtnOppilas.setSelected(true);
		rdbtnOppilas.setFocusable(false);
		rdbtnYllpito.setFocusable(false);

		bg.add(rdbtnOppilas);
		bg.add(rdbtnYllpito);

		rdbtnOppilas.addActionListener(this);
		rdbtnYllpito.addActionListener(this);

		tfEtunimi = new JTextField();
		tfEtunimi.setColumns(10);
		tfEtunimi.addKeyListener(enterListener);

		tfSukunimi = new JTextField();
		tfSukunimi.setColumns(10);
		tfSukunimi.addKeyListener(enterListener);

		btnLis.setFocusable(false);
		btnLis.addActionListener(this);

		btnPalaa.setFocusable(false);
		btnPalaa.addActionListener(this);

		pfSalasana = new JPasswordField();
		pfSalasana.addKeyListener(enterListener);

		pfSalasanaUudestaan = new JPasswordField();
		pfSalasanaUudestaan.addKeyListener(enterListener);

		ryhmaData = Database.getRyhmat().toArray(new Ryhma[Database.getRyhmat().size()]);
		
		cbRyhma = new JComboBox<Ryhma>(ryhmaData);
		cbRyhma.setFocusable(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(224)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRyhm)
								.addComponent(lblSukunimi)
								.addComponent(lblEtunimi))
							.addGap(44))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblKayttajatunnus)
							.addComponent(lblSalasana, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSalasanaUudestaan, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblOikeudet)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tfKayttajatunnus, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(tfEtunimi, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnOppilas)
							.addGap(18)
							.addComponent(rdbtnYllpito))
						.addComponent(tfSukunimi, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(pfSalasana, 221, 250, Short.MAX_VALUE)
						.addComponent(pfSalasanaUudestaan, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(cbRyhma, 0, 250, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLis, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(btnPalaa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(259))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addContainerGap(738, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(134)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfKayttajatunnus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKayttajatunnus))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pfSalasana, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalasana))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pfSalasanaUudestaan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalasanaUudestaan))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnOppilas)
							.addComponent(lblOikeudet))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnYllpito)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfEtunimi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEtunimi))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSukunimi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSukunimi))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRyhm)
						.addComponent(cbRyhma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPalaa)
						.addComponent(btnLis))
					.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	/**
	 * Värjää virheellisen kentän labelin punaiseksi ja näyttää virhesanoman.
	 */
	private void checkTextFields() {
		if (tfKayttajatunnus.getText().length() < 5 || tfKayttajatunnus.getText().length() > 15) lblKayttajatunnus.setForeground(Color.RED);
		if (pfSalasana.getPassword().length < 5 || pfSalasana.getPassword().length > 30) lblSalasana.setForeground(Color.RED);
		if (pfSalasanaUudestaan.getPassword().length < 5 || pfSalasanaUudestaan.getPassword().length > 30) lblSalasanaUudestaan.setForeground(Color.RED);
		if (tfEtunimi.getText().equals("") || tfEtunimi.getText().length() > 20) lblEtunimi.setForeground(Color.RED);
		if (tfSukunimi.getText().equals("") || tfSukunimi.getText().length() > 30) lblSukunimi.setForeground(Color.RED);
		
		if (tfKayttajatunnus.getText().length() > 15) JOptionPane.showMessageDialog(this, "Käyttäjätunnus voi olla enintään 15 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (pfSalasana.getPassword().length > 30) JOptionPane.showMessageDialog(this, "Salasana voi olla enintään 30 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (pfSalasanaUudestaan.getPassword().length > 30) JOptionPane.showMessageDialog(this, "Salasana voi olla enintään 30 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (tfEtunimi.getText().length() > 20) JOptionPane.showMessageDialog(this, "Etunimi voi olla enintään 20 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (tfSukunimi.getText().length() > 30) JOptionPane.showMessageDialog(this, "Sukunimi voi olla enintään 30 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);	
		
		else if (tfKayttajatunnus.getText().length() < 5) JOptionPane.showMessageDialog(this, "Käyttäjätunnus pitää olla vähintään 5 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (pfSalasana.getPassword().length < 5) JOptionPane.showMessageDialog(this, "Salasana pitää olla vähintään 5 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (pfSalasanaUudestaan.getPassword().length < 5) JOptionPane.showMessageDialog(this, "Salasana pitää olla vähintään 5 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (tfEtunimi.getText().equals("")) JOptionPane.showMessageDialog(this, "Etunimi ei voi olla tyhjä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		else if (tfSukunimi.getText().equals("")) JOptionPane.showMessageDialog(this, "Sukunimi ei voi olla tyhjä.", "Virhe", JOptionPane.ERROR_MESSAGE);
		
	}
	
	/**
	 * Lisää käyttäjän ja tarkistaa kenttien tiedot.
	 */
	private void addUser() {
		resetLabelColors();
		if (rdbtnOppilas.isSelected() && (tfEtunimi.getText().equals("") || tfEtunimi.getText().length() > 20 || tfSukunimi.getText().equals("") || tfSukunimi.getText().length() > 30 || tfKayttajatunnus.getText().length() < 5 || tfKayttajatunnus.getText().length() > 15 || pfSalasana.getPassword().length < 5 || pfSalasana.getPassword().length > 30 || pfSalasanaUudestaan.getPassword().length < 5 || pfSalasanaUudestaan.getPassword().length > 30)) {
			checkTextFields();
		} else if (rdbtnYllpito.isSelected() && (tfKayttajatunnus.getText().length() < 5 || tfKayttajatunnus.getText().length() > 15 || pfSalasana.getPassword().length < 5 || pfSalasana.getPassword().length > 30 || pfSalasanaUudestaan.getPassword().length < 5 || pfSalasanaUudestaan.getPassword().length > 30)) {
			checkTextFields();
		} else {
			if (ripoffPassword(pfSalasana.getPassword()).equals(ripoffPassword(pfSalasanaUudestaan.getPassword()))) {
				if (rdbtnOppilas.isSelected()) {
					Oppilas o = new Oppilas(tfEtunimi.getText(), tfSukunimi.getText(), (Ryhma)cbRyhma.getSelectedItem());
					Kayttajatunnus k = new Kayttajatunnus(tfKayttajatunnus.getText(), ripoffPassword(pfSalasana.getPassword()), Oikeudet, o);
					Database.lisaaKayttajatunnus(k, o);
					clearTextFields();
				} else if (rdbtnYllpito.isSelected()) {
					Kayttajatunnus k = new Kayttajatunnus(tfKayttajatunnus.getText(), ripoffPassword(pfSalasana.getPassword()), Oikeudet);
					Database.lisaaKayttajatunnus(k);
					clearTextFields();
				}
			} else {
				lblSalasana.setForeground(Color.RED);
				lblSalasanaUudestaan.setForeground(Color.RED);
				JOptionPane.showMessageDialog(this, "Salasana kentät eivät ole samoja.", "Virhe", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Tyhjentää kentät.
	 */
	private void clearTextFields() {
		tfEtunimi.setText("");
		tfSukunimi.setText("");
		tfKayttajatunnus.setText("");
		pfSalasana.setText("");
		pfSalasanaUudestaan.setText("");
	}
	
	/**
	 * Resetoi labeleitten värit.
	 */
	private void resetLabelColors() {
		lblKayttajatunnus.setForeground(Color.BLACK);
		lblSalasana.setForeground(Color.BLACK);
		lblSalasanaUudestaan.setForeground(Color.BLACK);
		lblEtunimi.setForeground(Color.BLACK);
		lblSukunimi.setForeground(Color.BLACK);
	}
	
	/**
	 * Hakee salasanan char taulukkoon salasana kentistä ja tekee siitä stringin.
	 * @return palauttaa salasanan
	 */
	private String ripoffPassword(char[] password) {
		String pw = "";
		for (int i = 0; i < password.length; i++) {
			pw += password[i];
		}
		return pw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (rdbtnOppilas == e.getSource()) {
			tfEtunimi.setVisible(true);
			tfSukunimi.setVisible(true);
			cbRyhma.setVisible(true);
			lblEtunimi.setVisible(true);
			lblSukunimi.setVisible(true);
			lblRyhm.setVisible(true);
			Oikeudet = 0;
		} else if (rdbtnYllpito == e.getSource()) {
			tfEtunimi.setVisible(false);
			tfSukunimi.setVisible(false);
			cbRyhma.setVisible(false);
			lblEtunimi.setVisible(false);
			lblSukunimi.setVisible(false);
			lblRyhm.setVisible(false);
			Oikeudet = 1;
		} else if (btnLis == e.getSource()) {
			addUser();
		} else if (btnPalaa == e.getSource()) {
			paaikkuna.edellinenPaneeli();
		}
	}
}