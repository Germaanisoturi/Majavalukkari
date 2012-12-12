package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kayttajatunnus;
import fi.majavapaja.lukkari.Paaikkuna;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KirjautumisPaneeli extends JPanel {
	private JTextField kayttajatunnusField;
	private JPasswordField salasanaField;

	private String kayttajatunnus;
	private String salasana;

	private Paaikkuna paaikkuna;
	/**
	 * Create the panel.
	 */
	public KirjautumisPaneeli(Paaikkuna paaikkuna) {
		this.paaikkuna = paaikkuna;
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);

		KeyAdapter enterAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					kirjaudu();
				}
			}
		};
		kayttajatunnusField = new JTextField();
		kayttajatunnusField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kayttajatunnusField.setBounds(326, 206, 156, 30);
		add(kayttajatunnusField);
		kayttajatunnusField.setColumns(10);
		kayttajatunnusField.addKeyListener(enterAdapter);

		JLabel kayttajatunnusLabel = new JLabel("Käyttäjätunnus");
		kayttajatunnusLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kayttajatunnusLabel.setBounds(326, 165, 156, 30);
		add(kayttajatunnusLabel);

		JLabel salasanaLabel = new JLabel("Salasana");
		salasanaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		salasanaLabel.setBounds(326, 247, 156, 30);
		add(salasanaLabel);

		JButton kirjauduButton = new JButton("Kirjaudu sisään");
		kirjauduButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kirjaudu();
			}
		});
		kirjauduButton.setBounds(326, 353, 156, 30);
		add(kirjauduButton);

		salasanaField = new JPasswordField();
		salasanaField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		salasanaField.setBounds(326, 288, 156, 30);
		add(salasanaField);
		salasanaField.addKeyListener(enterAdapter);
	}

	protected void kirjaudu() {
		kayttajatunnus = kayttajatunnusField.getText();
		salasana = ripoffPassword(salasanaField.getPassword());

		Kayttajatunnus kayttaja = Database.tarkastaKirjautuminen(kayttajatunnus, salasana);

		if (kayttaja == null) fail();
		else {
			if (kayttaja.getSalasana().equals(salasana)) {
				//JOptionPane.showMessageDialog(null, "Kirjautuminen onnistui!");
				paaikkuna.kirjaudu(kayttaja);
			}
			else fail();
		}
	}

	private String ripoffPassword(char[] password) {
		String jotain = "";
		for (int i = 0; i < password.length; i++) {
			jotain += password[i];
		}
		return jotain;
	}

	private void fail() {
		salasanaField.setText("");
		JOptionPane.showMessageDialog(null, "Kirjautuminen epäonnistui!");
	}
}
