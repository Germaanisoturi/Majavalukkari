package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

@SuppressWarnings("serial")
public class EtsiRyhma extends JPanel {
	private JTextField ryhmanNimiField;
	private JList<Ryhma> ryhmaList;
	private Paaikkuna ikkuna;
	
	public EtsiRyhma(Paaikkuna ikkuna) {
		this.ikkuna = ikkuna;
		setPreferredSize(new Dimension(800, 600));
		JLabel lblRyhmanNimi = new JLabel("Ryhmän nimi");
		
		ryhmanNimiField = new JTextField();
		lblRyhmanNimi.setLabelFor(ryhmanNimiField);
		ryhmanNimiField.setColumns(10);
		
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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRyhmanNimi)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ryhmanNimiField))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnEtsi, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addGap(131))
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
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
		);
		
		ryhmaList = new JList<Ryhma>();
		scrollPane.setViewportView(ryhmaList);
		setLayout(groupLayout);
		ikkuna.pack();
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
