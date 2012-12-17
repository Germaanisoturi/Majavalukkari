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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

/**
 * EtsiRyhma paneelissa etsitään ryhmä muokkaamista varten.
 *
 * @author Majavapaja
 */
@SuppressWarnings("serial")
public class EtsiRyhma extends JPanel {
	private JTextField ryhmanNimiField;
	private JList<Ryhma> ryhmaList;
	private Paaikkuna ikkuna;
	
	public EtsiRyhma(Paaikkuna ikkuna) {
		this.ikkuna = ikkuna;
		setSize(800, 600);
		JLabel lblRyhmanNimi = new JLabel("Ryhmän nimi");
		
		KeyAdapter enterAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					etsiActionPerformed();
				}
			}
		};
		
		ryhmanNimiField = new JTextField();
		lblRyhmanNimi.setLabelFor(ryhmanNimiField);
		ryhmanNimiField.setColumns(10);
		ryhmanNimiField.addKeyListener(enterAdapter);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnEtsi = new JButton("Etsi");
		btnEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				etsiActionPerformed();
			}
		});
		
		JButton btnTakaisin = new JButton("Takaisin");
		btnTakaisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takaisinActionPerformed();
			}
		});
		
		JButton btnMuokkaa = new JButton("Muokkaa");
		btnMuokkaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muokkaaActionPerformed();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblRyhmanNimi)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(ryhmanNimiField))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnEtsi, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
							.addGap(131))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
								.addComponent(btnMuokkaa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(554))))
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
						.addComponent(btnEtsi)
						.addComponent(btnTakaisin))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnMuokkaa)
					.addContainerGap())
		);
		
		ryhmaList = new JList<Ryhma>();
		scrollPane.setViewportView(ryhmaList);
		setLayout(groupLayout);
	}

	protected void muokkaaActionPerformed() {
		int i = ryhmaList.getSelectedIndex();
		if (i == -1)
			return;
		Ryhma ryhma = (Ryhma) ryhmaList.getModel().getElementAt(i);
		ikkuna.vaihdaPaneeli(new MuokkaaRyhmaa(ikkuna, ryhma));
	}

	private void etsiActionPerformed() {
		String nimi = ryhmanNimiField.getText().trim();
		
		List<Ryhma> ryhmat = Database.haeRyhmat(nimi);
		
		if (ryhmat == null) {
			System.out.println("LOOOOL NULLL");
			return;
		}
		
		ryhmaList.setListData(ryhmat.toArray(new Ryhma[0]));
	}

	private void takaisinActionPerformed() {
		ikkuna.edellinenPaneeli();
	}
}
