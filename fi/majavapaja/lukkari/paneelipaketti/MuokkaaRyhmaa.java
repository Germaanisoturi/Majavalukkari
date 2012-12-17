package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

/**
 * MuokkaaRyhmaa paneelissa muokataan ryhmää.
 * 
 * @author Majavapaja
 */
@SuppressWarnings("serial")
public class MuokkaaRyhmaa extends JPanel {
	private JTextField ryhmanNimiField;
	private Paaikkuna ikkuna;
	private Ryhma ryhma;
	
	public MuokkaaRyhmaa(Paaikkuna ikkuna, Ryhma ryhma) {
		this.ikkuna = ikkuna;
		this.ryhma = ryhma;
		setSize(800, 600);
		
		JLabel lblRyhmanNimi = new JLabel("Ryhmän nimi");
		
		ryhmanNimiField = new JTextField();
		ryhmanNimiField.setColumns(10);
		
		JButton btnTallenna = new JButton("Tallenna");
		btnTallenna.addActionListener(new ActionListener() {
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
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRyhmanNimi)
							.addGap(18)
							.addComponent(ryhmanNimiField, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnTallenna, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnTakaisin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(251, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRyhmanNimi)
						.addComponent(ryhmanNimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTallenna)
						.addComponent(btnTakaisin))
					.addContainerGap(235, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		taytaKentat();
	}
	
	private void taytaKentat() {
		ryhmanNimiField.setText(ryhma.getNimi());
	}

	protected void tallennaActionPerformed() {
		String ryhmanNimi = ryhmanNimiField.getText().trim();
		if ("".equals(ryhmanNimi)) {
			// TODO: Virheilmoitus
			return;
		}
		
		ryhma.setNimi(ryhmanNimi);
		boolean onnistui = Database.updateRyhma(ryhma);
		if (onnistui) {
			JOptionPane.showMessageDialog(this, "Tietojen päivitys onnistui");
			ikkuna.edellinenPaneeli();
		} else {
			JOptionPane.showMessageDialog(this, "Tietojen päivitys epäonnistui");
		}
	}

	protected void takaisinActionPerformed() {
		ikkuna.edellinenPaneeli();
	}
}
