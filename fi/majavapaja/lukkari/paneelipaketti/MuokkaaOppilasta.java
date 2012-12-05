package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Oppilas;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

@SuppressWarnings("serial")
public class MuokkaaOppilasta extends JPanel {
	private Paaikkuna paaikkuna;
	private Oppilas oppilas;
	private JTextField etunimiField;
	private JTextField sukunimiField;
	private JComboBox ryhmaComboBox;

	public MuokkaaOppilasta(Paaikkuna paaikkuna, Oppilas oppilas) {
		this.paaikkuna = paaikkuna;
		this.oppilas = oppilas;

		setSize(800, 600);

		JLabel lblEtunimi = new JLabel("Etunimi");

		JLabel lblSukunimi = new JLabel("Sukunimi");

		JLabel lblRyhm = new JLabel("Ryhm�");

		etunimiField = new JTextField();
		lblEtunimi.setLabelFor(etunimiField);
		etunimiField.setColumns(10);
		etunimiField.setText(oppilas.getEtunimi());

		sukunimiField = new JTextField();
		sukunimiField.setColumns(10);
		sukunimiField.setText(oppilas.getSukunimi());

		ryhmaComboBox = new JComboBox(new DefaultComboBoxModel(Database
				.getRyhmat().toArray()));
		ryhmaComboBox.setSelectedItem(oppilas.getRyhma());

		JButton btnTallennaMuutokset = new JButton("Tallenna muutokset");
		btnTallennaMuutokset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallennaActionPerformed();
			}
		});

		JButton btnTakaisin = new JButton("Takaisin");
		btnTakaisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takaisinActionPerformed();
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblEtunimi)
																		.addGap(18)
																		.addComponent(
																				etunimiField,
																				GroupLayout.DEFAULT_SIZE,
																				306,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblSukunimi)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				sukunimiField,
																				GroupLayout.DEFAULT_SIZE,
																				307,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblRyhm)
																		.addGap(18)
																		.addComponent(
																				ryhmaComboBox,
																				0,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnTallennaMuutokset,
																				GroupLayout.PREFERRED_SIZE,
																				174,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnTakaisin,
																				GroupLayout.PREFERRED_SIZE,
																				148,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(432)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblEtunimi)
														.addComponent(
																etunimiField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblSukunimi)
														.addComponent(
																sukunimiField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblRyhm)
														.addComponent(
																ryhmaComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnTallennaMuutokset)
														.addComponent(
																btnTakaisin))
										.addContainerGap(466, Short.MAX_VALUE)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnTallennaMuutokset, btnTakaisin });
		setLayout(groupLayout);
	}

	protected void takaisinActionPerformed() {
		paaikkuna.edellinenPaneeli();
	}

	protected void tallennaActionPerformed() {
		String etunimi = etunimiField.getText().trim();
		String sukunimi = sukunimiField.getText().trim();
		Ryhma ryhma = (Ryhma) ryhmaComboBox.getModel().getSelectedItem();

		// TODO: Käsittele kunnolla
		if (ryhma == null)
			return;
		if ("".equals(etunimi))
			return;
		if ("".equals(sukunimi))
			return;

		oppilas.setEtunimi(etunimi);
		oppilas.setSukunimi(sukunimi);
		oppilas.setRyhma(ryhma);

		boolean onnistui = Database.updateOppilas(oppilas);
		if (onnistui) {
			JOptionPane.showMessageDialog(this, "Tietojen päivitys onnistui");
			paaikkuna.edellinenPaneeli();
		} else {
			JOptionPane
					.showMessageDialog(this, "Tietojen päivitys epäonnistui");
		}
	}
}
