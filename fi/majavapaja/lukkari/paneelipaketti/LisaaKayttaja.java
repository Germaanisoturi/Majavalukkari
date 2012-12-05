package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	private JButton ÇŒÆØﬂßƒﬁ = new JButton("       ");
	private String[] blah = { "∞", "8" , "☺", "‽", "Ç", "▌"};
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

		ÇŒÆØﬂßƒﬁ.setFocusable(false);
		ÇŒÆØﬂßƒﬁ.addActionListener(this);
		// ÇŒÆØﬂßƒﬁ.setBorder(null);
		ÇŒÆØﬂßƒﬁ.setBorderPainted(false);
		ÇŒÆØﬂßƒﬁ.setContentAreaFilled(false);
		ÇŒÆØﬂßƒﬁ.setOpaque(false);

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
					.addComponent(ÇŒÆØﬂßƒﬁ)
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
					.addComponent(ÇŒÆØﬂßƒﬁ)
					.addContainerGap())
		);
		setLayout(groupLayout);

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
		} else if (rdbtnYllpito == e.getSource()) {
			tfEtunimi.setVisible(false);
			tfSukunimi.setVisible(false);
			comboBox.setVisible(false);
			lblEtunimi.setVisible(false);
			lblSukunimi.setVisible(false);
			lblRyhm.setVisible(false);
		} else if (btnLis == e.getSource()) {
			
			Kayttajatunnus k = new Kayttajatunnus(tfEtunimi.getText(), ripoffPassword(pfSalasana.getPassword()), Oikeudet);
			Oppilas o = new Oppilas(tfEtunimi.getText(), tfSukunimi.getText(), (Ryhma)comboBox.getSelectedItem());
			
			System.out.println("us: " + tfKayttajatunnus.getText() + " pw1: "
					+ ripoffPassword(pfSalasana.getPassword()) + " pw2: "
					+ripoffPassword(pfSalasanaUudestaan.getPassword()) + " fna: "
					+ tfEtunimi.getText() + " lna: " + tfSukunimi.getText());
		} else if (btnPalaa == e.getSource()) {
			paaikkuna.edellinenPaneeli();
			System.out.println("blaa");
		} else if (ÇŒÆØﬂßƒﬁ == e.getSource()) {
			Ryhma r = (Ryhma) comboBox.getSelectedItem();
			if (r.getNimi().equals("Ç") && rdbtnYllpito.isSelected()) {
				System.out.println("vblah");
				new Thread() {
					int asd = 0;

					public void run() {
						while (true) {
							asd++;
							if (asd >= blah.length)
								asd = 0;
							String text = blah[asd];

							tfKayttajatunnus.setText(text);
							tfEtunimi.setText(text);
							tfSukunimi.setText(text);
							pfSalasana.setText(text);
							pfSalasanaUudestaan.setText(text);
								lblEtunimi.setText(text);
								lblKayttajatunnus.setText(text);
								lblOikeudet.setText(text);
								lblRyhm.setText(text);
								lblSalasana.setText(text);
								lblSalasanaUudestaan.setText(text);
								lblSukunimi.setText(text);
								rdbtnOppilas.setText(text);
								rdbtnYllpito.setText(text);
								comboBox.removeAllItems();
								btnLis.setText(text);
								btnPalaa.setText(text);

							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			} else {
				System.out.println("ÇŒÆØﬂßƒﬁ");
				tfKayttajatunnus.setText("ÇŒÆØﬂßƒﬁ");
				tfEtunimi.setText("ÇŒÆØﬂßƒﬁ");
				tfSukunimi.setText("ÇŒÆØﬂßƒﬁ");
				pfSalasana.setText("ÇŒÆØﬂßƒﬁ");
				pfSalasanaUudestaan.setText("ÇŒÆØﬂßƒﬁ");

			}
		}
	}
}