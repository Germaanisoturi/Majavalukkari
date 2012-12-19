package fi.majavapaja.lukkari.paneelipaketti;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Kayttajatunnus;
import fi.majavapaja.lukkari.Menupalkki;
import fi.majavapaja.lukkari.Paaikkuna;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Kirjautumisessa kysytään käyttäjältä tunnuksia ja
 * avataan ohjelma tunnuksien mukaiseen näkymään.
 * 
 * $author Majavapaja
 */

public class Kirjautuminen extends JPanel {

    private JTextField kayttajatunnusField;
    private JPasswordField salasanaField;
    private String kayttajatunnus;
    private String salasana;
    private Paaikkuna ikkuna;

    /**
     * Luo sisäänkirjautumisen näkymän ja sijoittaa sen pääikkunaan.
     * 
     * @param paaikkuna pääikkuna, johon näkymä sijoitetaan.
     */
    public Kirjautuminen(Paaikkuna paaikkuna) {
        this.ikkuna = paaikkuna;
        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        KeyAdapter enterAdapter = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tarkistaKirjautuminen();
                }
            }
        };
        kayttajatunnusField = new JTextField();
        kayttajatunnusField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        kayttajatunnusField.setBounds(326, 206, 156, 30);
        add(kayttajatunnusField);
        kayttajatunnusField.setColumns(10);
        kayttajatunnusField.addKeyListener(enterAdapter);

        JLabel kayttajatunnusLabel = new JLabel("Käyttäjätunnus");
        kayttajatunnusLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        kayttajatunnusLabel.setBounds(326, 165, 156, 30);
        add(kayttajatunnusLabel);

        JLabel salasanaLabel = new JLabel("Salasana");
        salasanaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        salasanaLabel.setBounds(326, 247, 156, 30);
        add(salasanaLabel);

        JButton kirjauduButton = new JButton("Kirjaudu sisään");
        kirjauduButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tarkistaKirjautuminen();
            }
        });
        kirjauduButton.setBounds(326, 353, 156, 30);
        add(kirjauduButton);

        salasanaField = new JPasswordField();
        salasanaField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        salasanaField.setBounds(326, 288, 156, 30);
        add(salasanaField);
        salasanaField.addKeyListener(enterAdapter);
    }
    
    /**
     * Poimii kirjautumistiedot näkymästä ja tarkastaa
     * niiden oikeudet tietokannasta.
     */
    private void tarkistaKirjautuminen() {
        kayttajatunnus = kayttajatunnusField.getText();
        salasana = ripoffPassword(salasanaField.getPassword());

        Kayttajatunnus kayttaja = Database.tarkastaKirjautuminen(kayttajatunnus, salasana);

        if (kayttaja == null) {
            fail();
        } else {
            if (kayttaja.getSalasana().equals(salasana)) {
                kirjaudu(kayttaja);
            } else {
                fail();
            }
        }
    }

    /**
     * Kirjaa käyttäjän sisään ohjelmaan, eli luo
     * näkymät käyttäjän oikeuksien mukaisesti.
     *
     * @param ktunnus sisäänkirjautuvan käyttäjän tunnukset.
     */
    public void kirjaudu(Kayttajatunnus ktunnus) {
        ikkuna.setJMenuBar(new Menupalkki(ikkuna, ktunnus));
        if (ktunnus.getOppilas() != null) {
            ikkuna.edellinenPaneeli();
            ikkuna.vaihdaPaneeli(new MuokkaaLukkaria(ktunnus.getOppilas().getRyhma()));
        } else {
            ikkuna.edellinenPaneeli();
        }
    }

    private String ripoffPassword(char[] password) {
        String jotain = "";
        for (int i = 0; i < password.length; i++) {
            jotain += password[i];
        }
        return jotain;
    }

    private void fail() {
        salasanaField.setText("");
        JOptionPane.showMessageDialog(null, "Kirjautuminen epäonnistui!");
    }
}
