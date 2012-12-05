package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Oppilas;

@SuppressWarnings("serial")
public class EtsiOppilas extends JPanel {
	private JTextField etunimiField;
	private JTextField sukunimiField;
	private JList oppilaatList;

	/**
	 * Create the panel.
	 */
	public EtsiOppilas() {
		setSize(800, 600);
		
		JLabel lblEtunimi = new JLabel("Etunimi");
		
		etunimiField = new JTextField();
		lblEtunimi.setLabelFor(etunimiField);
		etunimiField.setColumns(10);
		
		JLabel lblSukunimi = new JLabel("Sukunimi");
		
		sukunimiField = new JTextField();
		lblSukunimi.setLabelFor(sukunimiField);
		sukunimiField.setColumns(10);
		
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
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
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
								.addComponent(etunimiField, GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
								.addComponent(sukunimiField, GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
								.addComponent(btnEtsi, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnTakaisin, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(textArea))))
					.addGap(0))
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
						.addComponent(btnTakaisin)
						.addComponent(btnEtsi))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		oppilaatList = new JList();
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
	
	private void takaisinActionPerformed(ActionEvent e) {}
	private void muokkaaOppilastaActionPerformed(ActionEvent e) {}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new EtsiOppilas());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
