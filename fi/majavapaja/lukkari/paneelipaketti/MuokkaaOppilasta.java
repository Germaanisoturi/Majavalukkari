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
import fi.majavapaja.lukkari.Kayttajatunnus;
import fi.majavapaja.lukkari.Oppilas;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

@SuppressWarnings("serial")
public class MuokkaaOppilasta extends JPanel {
	private Paaikkuna paaikkuna;
	private Kayttajatunnus kayttajatunnus;
	private JTextField etunimiField;
	private JTextField sukunimiField;
	private JComboBox ryhmaComboBox;
	private JTextField kayttajatunnusField;
	private JTextField salasanaField;

	public MuokkaaOppilasta(Paaikkuna paaikkuna, Kayttajatunnus kayttajatunnus) {
		this.paaikkuna = paaikkuna;
		this.kayttajatunnus = kayttajatunnus;

		setSize(800, 600);

		JLabel lblEtunimi = new JLabel("Etunimi");

		JLabel lblSukunimi = new JLabel("Sukunimi");

		JLabel lblRyhm = new JLabel("Ryhmä");

		etunimiField = new JTextField();
		lblEtunimi.setLabelFor(etunimiField);
		etunimiField.setColumns(10);

		sukunimiField = new JTextField();
		sukunimiField.setColumns(10);

		ryhmaComboBox = new JComboBox(new DefaultComboBoxModel(Database
				.getRyhmat().toArray()));

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
		
		JLabel lblKyttjtunnus = new JLabel("Käyttäjätunnus");
		
		JLabel lblSalasana = new JLabel("Salasana");
		
		kayttajatunnusField = new JTextField();
		lblKyttjtunnus.setLabelFor(kayttajatunnusField);
		kayttajatunnusField.setColumns(10);
		
		salasanaField = new JTextField();
		lblSalasana.setLabelFor(salasanaField);
		salasanaField.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRyhm)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblSukunimi)
											.addComponent(lblEtunimi))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(etunimiField, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
											.addComponent(sukunimiField, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
											.addComponent(ryhmaComboBox, Alignment.TRAILING, 0, 299, Short.MAX_VALUE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblKyttjtunnus)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(salasanaField, Alignment.LEADING)
											.addComponent(kayttajatunnusField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))))
							.addGap(432))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSalasana)
							.addContainerGap(747, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnTallennaMuutokset, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(432, Short.MAX_VALUE))))
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
						.addComponent(sukunimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSukunimi))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRyhm)
						.addComponent(ryhmaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKyttjtunnus)
						.addComponent(kayttajatunnusField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSalasana)
						.addComponent(salasanaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTallennaMuutokset)
						.addComponent(btnTakaisin))
					.addContainerGap(341, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnTallennaMuutokset, btnTakaisin});
		setLayout(groupLayout);
		
		taytaKentat();
	}
	
	private void taytaKentat() {
		if (kayttajatunnus.getOppilas() == null) {
			etunimiField.setEnabled(false);
			sukunimiField.setEnabled(false);
			ryhmaComboBox.setEnabled(false);
		} else {
			Oppilas o = kayttajatunnus.getOppilas();
			etunimiField.setText(o.getEtunimi());
			sukunimiField.setText(o.getSukunimi());
			ryhmaComboBox.setSelectedItem(o.getRyhma());
		}
		
		kayttajatunnusField.setText(kayttajatunnus.getKayttajanimi());
		salasanaField.setText(kayttajatunnus.getSalasana());
	}

	protected void takaisinActionPerformed() {
		paaikkuna.edellinenPaneeli();
	}

	protected void tallennaActionPerformed() {
//		String etunimi = etunimiField.getText().trim();
//		String sukunimi = sukunimiField.getText().trim();
//		Ryhma ryhma = (Ryhma) ryhmaComboBox.getModel().getSelectedItem();
//
//		// TODO: Käsittele kunnolla
//		if (ryhma == null)
//			return;
//		if ("".equals(etunimi))
//			return;
//		if ("".equals(sukunimi))
//			return;
//
//		kayttajatunnus.setEtunimi(etunimi);
//		kayttajatunnus.setSukunimi(sukunimi);
//		kayttajatunnus.setRyhma(ryhma);
//
//		boolean onnistui = Database.updateOppilas(kayttajatunnus);
//		if (onnistui) {
//			JOptionPane.showMessageDialog(this, "Tietojen päivitys onnistui");
//			paaikkuna.edellinenPaneeli();
//		} else {
//			JOptionPane
//					.showMessageDialog(this, "Tietojen päivitys epäonnistui");
//		}
	}
}
