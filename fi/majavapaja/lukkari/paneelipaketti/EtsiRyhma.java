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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Oppilas;
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
	private JList<Oppilas> oppilaatList;
	
	/**
	 * Luo ryhmänhakunäkymän.
	 * 
	 * @param ikkuna Paaikkuna, johon paneeli sijoitetaan.
	 */
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
								.addComponent(btnMuokkaa, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addGap(334))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblRyhmanNimi)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(ryhmanNimiField))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnEtsi, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
							.addGap(554))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRyhmanNimi)
						.addComponent(ryhmanNimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEtsi)
						.addComponent(btnTakaisin))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnMuokkaa)
					.addContainerGap())
		);
		
		oppilaatList = new JList<>();
		scrollPane_1.setViewportView(oppilaatList);
		
		ryhmaList = new JList<Ryhma>();
		ryhmaList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ryhmaListSelectionChanged(e);
			}
		});
		scrollPane.setViewportView(ryhmaList);
		setLayout(groupLayout);
	}

	/**
	 * Vaihtaa näkymäksi uuden MuokkaaRyhmaa paneelin, jos
	 * hakutuloksista on valittuna ryhmä.
	 */
	protected void muokkaaActionPerformed() {
		int i = ryhmaList.getSelectedIndex();
		if (i == -1)
			return;
		Ryhma ryhma = (Ryhma) ryhmaList.getModel().getElementAt(i);
		ikkuna.vaihdaPaneeli(new MuokkaaRyhmaa(ikkuna, ryhma));
	}
	
	/**
	 * Näyttää ryhmän oppilaat listassa, kun oppilas valitaan hakutulosten
	 * listasta.
	 */
	private void ryhmaListSelectionChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		int i = ryhmaList.getSelectedIndex();
		if (i == -1) {
			// Listassa ei ole enää valintaa, tyhjennä infoteksti
			return;
		}
		
		Ryhma r = (Ryhma) ryhmaList.getModel().getElementAt(i);
		List<Oppilas> oppilaat = Database.getRyhmanOppilaat(r);
		if (oppilaat == null)
			return; // Random virhe :O
		
		oppilaatList.setListData(oppilaat.toArray(new Oppilas[0]));
	}

	/**
	 * Hakee tietokannasta syötettyä nimeä vastaavat ryhmät ja
	 * lisää ne hakutulosten listaan.
	 */
	protected void etsiActionPerformed() {
		String nimi = ryhmanNimiField.getText().trim();
		
		List<Ryhma> ryhmat = Database.haeRyhmat(nimi);
		
		if (ryhmat == null) {
			JOptionPane.showMessageDialog(this, "Virhe haettaessa ryhmiä tietokannasta", "Virhe", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		ryhmaList.setListData(ryhmat.toArray(new Ryhma[0]));
	}

	/**
	 * Pyytää pääikkunaa palaamaan edelliseen paneeliin.
	 */
	protected void takaisinActionPerformed() {
		ikkuna.edellinenPaneeli();
	}
}
