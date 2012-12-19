package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kurssi;
import fi.majavapaja.lukkari.Paaikkuna;

/**
 * Kommunikoi kurssien lisäämisen käyttäjän kanssa.
 * 
 * @author Majavapaja
 */
public class LisaaKurssi extends JPanel {

    private Paaikkuna ikkuna;
    private JLabel jLabel1;
    private JTextField kurssiField;
    private JButton lisaaButton;
    private JButton takaisinButton;

    /**
     * Luo paneelin kurssien lisäämistä varten.
     * 
     * @param ikkuna pääikkuna, johonka paneeli tulee näkyviin.
     */
    public LisaaKurssi(Paaikkuna ikkuna) {
        initComponents();
        setSize(800, 600);
        this.ikkuna = ikkuna;
    }

    private void initComponents() {

        kurssiField = new JTextField();
        lisaaButton = new JButton();
        takaisinButton = new JButton();
        jLabel1 = new JLabel();

        setPreferredSize(new Dimension(800, 600));

        lisaaButton.setText("Lisää");
        lisaaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                lisaaButtonActionPerformed(evt);
            }
        });

        takaisinButton.setText("Takaisin");
        takaisinButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                takaisinButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Kurssin nimi:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(252, 252, 252).addComponent(jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(lisaaButton, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(takaisinButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addComponent(kurssiField)).addGap(333, 333, 333)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(290, 290, 290).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(kurssiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(jLabel1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lisaaButton).addComponent(takaisinButton)).addContainerGap(261, Short.MAX_VALUE)));
    }               

    private void lisaaButtonActionPerformed(ActionEvent evt) {
        if (!kurssiField.getText().isEmpty()) {
        	if (kurssiField.getText().length() > 50) {
            	JOptionPane.showMessageDialog(this, "Kurssin nimi voi olla enintään 50 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
        	} else {
        		Kurssi uusiKurssi = new Kurssi(kurssiField.getText());
        		if (Database.lisaaKurssi(uusiKurssi)) {
        			kurssiField.setText("");
        		}
        	}
        } else if (kurssiField.getText().isEmpty()) {
        	JOptionPane.showMessageDialog(this, "Kurssin nimi ei voi olla tyhjä.", "Virhe", JOptionPane.ERROR_MESSAGE);
        } else {
        	String message = "Tapahtui vakava tuntematon virhe!\n" + "Ottakaa välittömästi yhteys tirehtööriin!\n" + "Sähköposti: Henry.Heikkinen@majavapaja.fi";
        	JOptionPane.showMessageDialog(this, message, "Error #???", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void takaisinButtonActionPerformed(ActionEvent evt) {
        ikkuna.edellinenPaneeli();
    }
}
