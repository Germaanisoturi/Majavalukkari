package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Component;
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
import javax.swing.SwingConstants;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kurssi;
import fi.majavapaja.lukkari.Paaikkuna;

@SuppressWarnings("serial")
public class EtsiKurssi extends JPanel {
	private JTextField kurssinNimiField;
	private Paaikkuna ikkuna;
	private JList<Kurssi> kurssiList;
	
	public EtsiKurssi(Paaikkuna ikkuna) {
		this.ikkuna = ikkuna;
		setPreferredSize(new Dimension(800, 600));
		
		JLabel lblKurssinNimi = new JLabel("Kurssin nimi");
		
		kurssinNimiField = new JTextField();
		lblKurssinNimi.setLabelFor(kurssinNimiField);
		kurssinNimiField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnHae = new JButton("Hae");
		btnHae.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				haeActionPerformed();
			}
		});
		
		JButton btnTakaisin = new JButton("Takaisin");
		btnTakaisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takaisinActionPerformed();
			}
		});
		
		JButton btnMuokkaaKurssia = new JButton("Muokkaa kurssia");
		btnMuokkaaKurssia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muokkaaActionPerformed();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKurssinNimi)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(kurssinNimiField, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(622, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnHae, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnMuokkaaKurssia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
							.addGap(622))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKurssinNimi)
						.addComponent(kurssinNimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnHae)
						.addComponent(btnTakaisin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMuokkaaKurssia)
					.addContainerGap(260, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnHae, btnTakaisin});
		
		kurssiList = new JList<Kurssi>();
		scrollPane.setViewportView(kurssiList);
		setLayout(groupLayout);
	}
	
	protected void muokkaaActionPerformed() {
		throw new NotImplementedException();
	}
	
	protected void takaisinActionPerformed() {
		ikkuna.edellinenPaneeli();
	}
	
	protected void haeActionPerformed() {
		String nimi = kurssinNimiField.getText().trim();
		
		List<Kurssi> ryhmat = Database.haeKurssit(nimi);
		
		if (ryhmat == null) {
			System.out.println("LOOOOL NULLL");
			return;
		}
		
		kurssiList.setListData(ryhmat.toArray(new Kurssi[0]));
	}
}
