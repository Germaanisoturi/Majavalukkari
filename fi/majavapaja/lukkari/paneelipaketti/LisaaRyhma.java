package fi.majavapaja.lukkari.paneelipaketti;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

import javax.swing.*;

/**
 *
 * @author Majavapaja
 */
public class LisaaRyhma extends JPanel {

    private Paaikkuna ikkuna;
    private JLabel jLabel1;
    private JTextField ryhmaField;
    private JButton lisaaButton;
    private JButton takaisinButton;
    
    public LisaaRyhma(Paaikkuna ikkuna) {
        initComponents();
        setSize(800, 600);
        this.ikkuna = ikkuna;
    }

    private void initComponents() {

        ryhmaField = new JTextField();
        lisaaButton = new JButton();
        takaisinButton = new JButton();
        jLabel1 = new JLabel();

        setPreferredSize(new Dimension(800, 600));

        lisaaButton.setText("Lisää");
        lisaaButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                lisaaButtonActionPerformed(evt);
            }
        });

        takaisinButton.setText("Takaisin");
        takaisinButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                takaisinButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Ryhmän nimi:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(252, 252, 252).addComponent(jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(lisaaButton, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(takaisinButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addComponent(ryhmaField)).addGap(333, 333, 333)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(290, 290, 290).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(ryhmaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(jLabel1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lisaaButton).addComponent(takaisinButton)).addContainerGap(261, Short.MAX_VALUE)));
    }                      

    private void lisaaButtonActionPerformed(ActionEvent evt) {
        if (!ryhmaField.equals("")) {
        	if (ryhmaField.getText().length() > 30) {
            	JOptionPane.showMessageDialog(this, "Ryhmän nimi voi olla enintään 30 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
        	} else {
        		Ryhma uusiRyhma = new Ryhma(ryhmaField.getText());
        		if (Database.lisaaRyhma(uusiRyhma)) {
                ryhmaField.setText("");
        		}
        	}
        } else {
                String message = "Tapahtui vakava tuntematon virhe!\n" + "Ottakaa välittömästi yhteys tirehtööriin!\n" + "Sähköposti: Henry.Heikkinen@majavapaja.fi";
                JOptionPane.showMessageDialog(this, message, "Error #???", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void takaisinButtonActionPerformed(ActionEvent evt) {
        ikkuna.edellinenPaneeli();
    }
}
