package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import fi.majavapaja.lukkari.Paaikkuna;

public class LisaaKayttaja extends JPanel implements ActionListener {
	private JLabel lblNewLabel = new JLabel("Käyttäjätunnus:");
	private JLabel lblSalasana = new JLabel("Salasana:");
	private JLabel lblSalasanaUudestaan = new JLabel("Uudestaan:");
	private JLabel lblOikeudet = new JLabel("Oikeudet:");
	private JLabel lblEtunimi = new JLabel("Etunimi:");
	private JLabel lblSukunimi = new JLabel("Sukunimi:");
	private JLabel lblRyhm = new JLabel("Ryhmä:");
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JRadioButton rdbtnOppilas = new JRadioButton("Oppilas");
	private JRadioButton rdbtnYllpito = new JRadioButton("Ylläpito");
	private String[] stuff = { "ÇŒÆØﬂßƒﬁ", "Ç" };
	private JComboBox comboBox;
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

		textField = new JTextField();
		textField.setColumns(10);

		ButtonGroup bg = new ButtonGroup();

		rdbtnOppilas.setFocusable(false);
		rdbtnOppilas.setSelected(true);

		rdbtnYllpito.setFocusable(false);
		bg.add(rdbtnOppilas);
		bg.add(rdbtnYllpito);

		rdbtnOppilas.addActionListener(this);
		rdbtnYllpito.addActionListener(this);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

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

		passwordField = new JPasswordField();

		passwordField_1 = new JPasswordField();

		comboBox = new JComboBox<String>(stuff);

		
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
							.addComponent(lblNewLabel)
							.addComponent(lblSalasana, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSalasanaUudestaan, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblOikeudet)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnOppilas)
							.addGap(18)
							.addComponent(rdbtnYllpito))
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(passwordField, 221, 250, Short.MAX_VALUE)
						.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
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
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalasana))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEtunimi))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
	
	/*public static void main(String[] args) {
		LisaaKayttaja l = new LisaaKayttaja(paaikkuna);

		JFrame frame = new JFrame("blah");
		frame.getContentPane().add(l);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		if (rdbtnOppilas == e.getSource()) {
			textField_2.setVisible(true);
			textField_3.setVisible(true);
			comboBox.setVisible(true);
			lblEtunimi.setVisible(true);
			lblSukunimi.setVisible(true);
			lblRyhm.setVisible(true);
		} else if (rdbtnYllpito == e.getSource()) {
			textField_2.setVisible(false);
			textField_3.setVisible(false);
			comboBox.setVisible(false);
			lblEtunimi.setVisible(false);
			lblSukunimi.setVisible(false);
			lblRyhm.setVisible(false);
		} else if (btnLis == e.getSource()) {
			System.out.println("us: " + textField.getText() + " pw1: "
					+ passwordField.getText() + " pw2: "
					+ passwordField_1.getText() + " fna: "
					+ textField_2.getText() + " lna: " + textField_3.getText());
		} else if (btnPalaa == e.getSource()) {
			paaikkuna.edellinenPaneeli();
			System.out.println("blaa");
		} else if (ÇŒÆØﬂßƒﬁ == e.getSource()) {
			if (comboBox.getSelectedItem().equals("Ç") && rdbtnYllpito.isSelected()) {
				System.out.println("vblah");
				new Thread() {
					int asd = 0;

					public void run() {
						while (true) {
							asd++;
							if (asd >= blah.length)
								asd = 0;
							String text = blah[asd];

							textField.setText(text);
							textField_2.setText(text);
							textField_3.setText(text);
							passwordField.setText(text);
							passwordField_1.setText(text);
								lblEtunimi.setText(text);
								lblNewLabel.setText(text);
								lblOikeudet.setText(text);
								lblRyhm.setText(text);
								lblSalasana.setText(text);
								lblSalasanaUudestaan.setText(text);
								lblSukunimi.setText(text);
								rdbtnOppilas.setText(text);
								rdbtnYllpito.setText(text);
								comboBox.removeAllItems();
								comboBox.addItem(text);
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
				textField.setText("ÇŒÆØﬂßƒﬁ");
				textField_2.setText("ÇŒÆØﬂßƒﬁ");
				textField_3.setText("ÇŒÆØﬂßƒﬁ");
				passwordField.setText("ÇŒÆØﬂßƒﬁ");
				passwordField_1.setText("ÇŒÆØﬂßƒﬁ");

			}
		}
	}
}