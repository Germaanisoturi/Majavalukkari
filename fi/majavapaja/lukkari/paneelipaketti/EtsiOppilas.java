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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Oppilas;
import fi.majavapaja.lukkari.Paaikkuna;

@SuppressWarnings("serial")
public class EtsiOppilas extends JPanel {
	private JTextField etunimiField;
	private JTextField sukunimiField;
	private JList oppilaatList;
	private JTextArea oppilasInfoTextArea;
	private Paaikkuna paaikkuna;

	public EtsiOppilas(Paaikkuna paaikkuna) {
		this.paaikkuna = paaikkuna;
		setSize(800, 600);
		
		KeyAdapter enterAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					etsiActionPerformed(null);
				}
			}
		};
		
		JLabel lblEtunimi = new JLabel("Etunimi");
		
		etunimiField = new JTextField();
		lblEtunimi.setLabelFor(etunimiField);
		etunimiField.setColumns(10);
		etunimiField.addKeyListener(enterAdapter);
		
		JLabel lblSukunimi = new JLabel("Sukunimi");
		
		sukunimiField = new JTextField();
		lblSukunimi.setLabelFor(sukunimiField);
		sukunimiField.setColumns(10);
		sukunimiField.addKeyListener(enterAdapter);
		
		JButton btnEtsi = new JButton("Etsi");
		btnEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etsiActionPerformed(e);
			}
		});
		
		JButton btnTakaisin = new JButton("Takaisin");
		btnTakaisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takaisinActionPerformed(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		oppilasInfoTextArea = new JTextArea();
		oppilasInfoTextArea.setLineWrap(true);
		oppilasInfoTextArea.setEditable(false);
		
		JButton btnNewButton = new JButton("Muokkaa oppilasta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muokkaaOppilastaActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblEtunimi, Alignment.LEADING)
								.addComponent(lblSukunimi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(etunimiField, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
								.addComponent(sukunimiField, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
								.addComponent(btnEtsi, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(oppilasInfoTextArea)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
								.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
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
						.addComponent(lblSukunimi)
						.addComponent(sukunimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEtsi)
						.addComponent(btnTakaisin))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(oppilasInfoTextArea, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		oppilaatList = new JList();
		oppilaatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		oppilaatList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				oppilaatListSelectionChanged(e);
			}
		});
		scrollPane.setViewportView(oppilaatList);
		setLayout(groupLayout);
	}

	private void etsiActionPerformed(ActionEvent e) {
		String etunimi = etunimiField.getText().trim();
		String sukunimi = sukunimiField.getText().trim();
		
		List<Oppilas> oppilaat = Database.haeOppilaatByName(etunimi, sukunimi);
		if (oppilaat == null) {
			// FIXME: Käsittele kunnolla
			System.out.println("LOOOOL NULLL");
			return;
		}
		oppilaatList.setListData(oppilaat.toArray());
	}
	
	
	private void oppilaatListSelectionChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		int i = oppilaatList.getSelectedIndex();
		if (i == -1) {
			// Listassa ei ole enää valintaa, tyhjennä infoteksti
			oppilasInfoTextArea.setText("");
			return;
		}
		
		Oppilas oppilas = (Oppilas) oppilaatList.getModel().getElementAt(i);
		String infoteksti = "Nimi:\t" + oppilas.getEtunimi() + " " + oppilas.getSukunimi();
		infoteksti += "\nRyhmä:\t" + oppilas.getRyhma().getNimi();
		oppilasInfoTextArea.setText(infoteksti);
	}
	
	private void takaisinActionPerformed(ActionEvent e) {
		paaikkuna.edellinenPaneeli();
	}
	
	
	private void muokkaaOppilastaActionPerformed(ActionEvent e) {
		int i = oppilaatList.getSelectedIndex();
		if (i == -1)
			return;
		Oppilas oppilas = (Oppilas) oppilaatList.getModel().getElementAt(i);
		paaikkuna.vaihdaPaneeli(new MuokkaaOppilasta(paaikkuna, oppilas));
	}
}
