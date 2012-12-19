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
import fi.majavapaja.lukkari.Kurssi;
import fi.majavapaja.lukkari.Paaikkuna;

/**
 * Kommunikoi kurssien muokkaamisen käyttäjän kanssa.
 *
 * @author Majavapaja
 */
public class MuokkaaKurssia extends JPanel {

    private JTextField kurssinNimiField;
    private Paaikkuna ikkuna;
    private Kurssi kurssi;

    /**
     * Luo paneelin kurssien muokkausta varten.
     *
     * @param ikkuna pääikkuna, johonka paneeli tulee näkyviin.
     * @param kurssi muokattava kurssi.
     */
    public MuokkaaKurssia(Paaikkuna ikkuna, Kurssi kurssi) {
        this.ikkuna = ikkuna;
        this.kurssi = kurssi;
        setSize(800, 600);

        JLabel lblKurssinNimi = new JLabel("Kurssin nimi");

        kurssinNimiField = new JTextField();
        kurssinNimiField.setColumns(10);

        JButton btnTallenna = new JButton("Tallenna");
        btnTallenna.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tallennaActionPerformed();
            }
        });

        JButton btnTakaisin = new JButton("Takaisin");
        btnTakaisin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                takaisinActionPerformed();
            }
        });
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout.createSequentialGroup().addComponent(lblKurssinNimi).addGap(18).addComponent(kurssinNimiField, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)).addGroup(groupLayout.createSequentialGroup().addComponent(btnTallenna, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnTakaisin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap(251, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblKurssinNimi).addComponent(kurssinNimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnTallenna).addComponent(btnTakaisin)).addContainerGap(235, Short.MAX_VALUE)));
        setLayout(groupLayout);
        taytaKentat();
    }

    private void taytaKentat() {
        kurssinNimiField.setText(kurssi.getNimi());
    }

    /**
     * Suorittaa tallennus napin painalluksen, eli tallentaa kurssiin tehdyt
     * muokkaukset tietokantaan. Antaa virheilmoituksen, jos muokkaus on
     * epäkelpo.
     */
    protected void tallennaActionPerformed() {
        String kurssinNimi = kurssinNimiField.getText().trim();
        
        if (kurssinNimi.equals("")) {
            JOptionPane.showMessageDialog(this, "Kurssin nimi ei voi olla tyhjä!", "Virhe! Kurssilta puuttuu nimi.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (kurssinNimi.length() > 50) {
            JOptionPane.showMessageDialog(this, "Kurssin nimi ei voi olla yli 50 merkkiä pitkä.", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        kurssi.setNimi(kurssinNimi);
        boolean onnistui = Database.updateKurssi(kurssi);
        if (onnistui) {
            JOptionPane.showMessageDialog(this, "Tietojen päivitys onnistui");
            ikkuna.edellinenPaneeli();
        } else {
            JOptionPane.showMessageDialog(this, "Tietojen päivitys epäonnistui");
        }
    }
    
    /**
     * Vaihtaa pääikkunan näkymän takaisin edelliseen paneeliin.
     */
    protected void takaisinActionPerformed() {
        ikkuna.edellinenPaneeli();
    }
}
