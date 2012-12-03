package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

public class LisaaRyhma extends JPanel {
	private JTextField textField;

	private Paaikkuna paaikkuna = new Paaikkuna();

	/**
	 * Create the panel.
	 */
	public LisaaRyhma(Paaikkuna paaikkuna) {
		this.paaikkuna = paaikkuna;
		setSize(800, 600);

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnLis = new JButton("Lisää");
		btnLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lisaaRyhma();
			}
		});

		JButton btnPalaa = new JButton("Takaisin");
		btnPalaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takaisin();
			}
		});

		JLabel lblRyhmnNimi = new JLabel("Ryhmän nimi");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(285).addComponent(lblRyhmnNimi).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(textField, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE).addGroup(groupLayout.createSequentialGroup().addComponent(btnLis).addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnPalaa))).addContainerGap(288, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(211).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(lblRyhmnNimi)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnLis).addComponent(btnPalaa)).addContainerGap(335, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	protected void takaisin() {
		paaikkuna.edellinenPaneeli();
	}

	protected void lisaaRyhma() {
		if (Database.lisaaRyhma(new Ryhma(textField.getText()))) {
			textField.setText("");
		} else {
			String message = "Tapahtui vakava tuntematon virhe!\n" + "Ottakaa välittömästi yhteys tirehtööriin!\n" + "Sähköposti: Henry.Heikkinen@majavapaja.fi";
			JOptionPane.showMessageDialog(this, message, "Error #???", JOptionPane.ERROR_MESSAGE);
		}
	}
}
