package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import sun.misc.FpUtils;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kayttajatunnus;
import fi.majavapaja.lukkari.Oppilas;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

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
	private Ryhma[] stuff;
	private JComboBox<Ryhma> comboBox;
	private JButton btnLis = new JButton("Lisää");
	private JButton btnPalaa = new JButton("Takaisin");
	private Paaikkuna paaikkuna = new Paaikkuna();;

	/**
	 * Create the panel.
	 */

	public LisaaKayttaja(Paaikkuna paaikkuna) {

		this.paaikkuna = paaikkuna;
		
		setSize(800, 600);

		tfKayttajatunnus = new JTextField();
		tfKayttajatunnus.setColumns(10);

		ButtonGroup bg = new ButtonGroup();

		rdbtnOppilas.setFocusable(false);
		rdbtnOppilas.setSelected(true);

		rdbtnYllpito.setFocusable(false);
		bg.add(rdbtnOppilas);
		bg.add(rdbtnYllpito);

		rdbtnOppilas.addActionListener(this);
		rdbtnYllpito.addActionListener(this);

		tfEtunimi = new JTextField();
		tfEtunimi.setColumns(10);

		tfSukunimi = new JTextField();
		tfSukunimi.setColumns(10);

		btnLis.setFocusable(false);
		btnLis.addActionListener(this);

		btnPalaa.setFocusable(false);
		btnPalaa.addActionListener(this);

		pfSalasana = new JPasswordField();

		pfSalasanaUudestaan = new JPasswordField();

		stuff = Database.getRyhmat().toArray(new Ryhma[Database.getRyhmat().size()]);
		comboBox = new JComboBox<Ryhma>(stuff);

		
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
						.addComponent(comboBox, 0, 250, Short.MAX_VALUE)
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
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPalaa)
						.addComponent(btnLis))
					.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	private void checkEmptyFields() {
		if (tfKayttajatunnus.getText().length() < 5 || tfKayttajatunnus.getText().length() > 15) lblKayttajatunnus.setForeground(Color.RED);
		if (pfSalasana.getPassword().length < 5 || pfSalasana.getPassword().length > 30) lblSalasana.setForeground(Color.RED);
		if (pfSalasanaUudestaan.getPassword().length < 5 || pfSalasanaUudestaan.getPassword().length > 30) lblSalasanaUudestaan.setForeground(Color.RED);
		if (tfEtunimi.getText().equals("") || tfEtunimi.getText().length() > 20) lblEtunimi.setForeground(Color.RED);
		if (tfSukunimi.getText().equals("") || tfSukunimi.getText().length() > 30) lblSukunimi.setForeground(Color.RED);

		
		if (tfKayttajatunnus.getText().length() < 5) {
			String message = "Kayttajatunnus pitää olla vähintään 5 merkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (pfSalasana.getPassword().length < 5) {
			String message = "Salasana pitää olla vähintään 5 merkkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (pfSalasanaUudestaan.getPassword().length < 5) {
			String message = "Salasana pitää olla vähintään 5 merkkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (tfEtunimi.getText().equals("")) {
			String message = "Etunimi ei voi olla tyhjä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (tfSukunimi.getText().equals("")) {
			String message = "Sukunimi ei voi olla tyhjä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		
		if (tfKayttajatunnus.getText().length() > 15) {
			String message = "Kayttajatunnus voi olla enintään 15 merkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (pfSalasana.getPassword().length > 30) {
			String message = "Salasana voi olla enintään 30 merkkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (pfSalasanaUudestaan.getPassword().length > 30) {
			String message = "Salasana voi olla enintään 30 merkkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (tfEtunimi.getText().length() > 20) {
			String message = "Etunimi voi olla enintään 20 merkkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		} else if (tfSukunimi.getText().length() > 30) {
			String message = "Sukunimi voi olla enintään 30 merkkiä pitkä.";
			JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void clearTextFields() {
		tfEtunimi.setText("");
		tfSukunimi.setText("");
		tfKayttajatunnus.setText("");
		pfSalasana.setText("");
		pfSalasanaUudestaan.setText("");
	}
	
	private String ripoffPassword(char[] password) {
		String jotain = "";
		for (int i = 0; i < password.length; i++) {
			jotain += password[i];
		}
		return jotain;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (rdbtnOppilas == e.getSource()) {
			tfEtunimi.setVisible(true);
			tfSukunimi.setVisible(true);
			comboBox.setVisible(true);
			lblEtunimi.setVisible(true);
			lblSukunimi.setVisible(true);
			lblRyhm.setVisible(true);
			Oikeudet = 0;
		} else if (rdbtnYllpito == e.getSource()) {
			tfEtunimi.setVisible(false);
			tfSukunimi.setVisible(false);
			comboBox.setVisible(false);
			lblEtunimi.setVisible(false);
			lblSukunimi.setVisible(false);
			lblRyhm.setVisible(false);
			Oikeudet = 1;
		} else if (btnLis == e.getSource()) {
			lblKayttajatunnus.setForeground(Color.BLACK);
			lblSalasana.setForeground(Color.BLACK);
			lblSalasanaUudestaan.setForeground(Color.BLACK);
			lblEtunimi.setForeground(Color.BLACK);
			lblSukunimi.setForeground(Color.BLACK);
			
			if (tfEtunimi.getText().equals("") || tfEtunimi.getText().length() > 20 || tfSukunimi.getText().equals("") || tfSukunimi.getText().length() > 30 || tfKayttajatunnus.getText().length() < 5 || tfKayttajatunnus.getText().length() > 15 || pfSalasana.getPassword().length < 5 || pfSalasana.getPassword().length > 30 || pfSalasanaUudestaan.getPassword().length < 5 || pfSalasanaUudestaan.getPassword().length > 30 ) {
				checkEmptyFields();
			} else {
				if (ripoffPassword(pfSalasana.getPassword()).equals(ripoffPassword(pfSalasanaUudestaan.getPassword()))) {
					if (rdbtnOppilas.isSelected()) {
						Oppilas o = new Oppilas(tfEtunimi.getText(), tfSukunimi.getText(), (Ryhma)comboBox.getSelectedItem());
						Kayttajatunnus k = new Kayttajatunnus(tfKayttajatunnus.getText(), ripoffPassword(pfSalasana.getPassword()), Oikeudet, o);
//						Database.lisaaKayttajatunnus(k, o);
						clearTextFields();
					} else if(rdbtnYllpito.isSelected()) {
						Kayttajatunnus k = new Kayttajatunnus(tfKayttajatunnus.getText(), ripoffPassword(pfSalasana.getPassword()), Oikeudet);
//						Database.lisaaKayttajatunnus(k);
						clearTextFields();
					}
				} else {
					lblSalasana.setForeground(Color.RED);
					lblSalasanaUudestaan.setForeground(Color.RED);
					String message = "Salasana kentät eivät ole samoja.";
					JOptionPane.showMessageDialog(this, message, "Virhe", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (btnPalaa == e.getSource()) {
			paaikkuna.edellinenPaneeli();
		}
	}
}