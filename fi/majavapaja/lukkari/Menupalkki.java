/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.majavapaja.lukkari;

import fi.majavapaja.lukkari.paneelipaketti.EtsiOppilas;
import fi.majavapaja.lukkari.paneelipaketti.LisaaKayttaja;
import fi.majavapaja.lukkari.paneelipaketti.LisaaKurssi;
import fi.majavapaja.lukkari.paneelipaketti.LisaaRyhma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author s1001069
 */
public class Menupalkki extends JMenuBar implements ActionListener {

    private Paaikkuna paaikkuna;

    public Menupalkki(Paaikkuna paaikkuna) {
        this.paaikkuna = paaikkuna;
        luoTiedostoMenu();
        luoLisaaMenu();
        luoNaytaMenu();
    }

    private void luoTiedostoMenu() {
        JMenu tiedostoMenu = new JMenu("Tiedosto");
        tiedostoMenu.setMnemonic('T');

        JMenuItem tulosta = new JMenuItem("Tulosta lukkari");
        tulosta.setMnemonic('T');
        tulosta.addActionListener(this);
        tiedostoMenu.add(tulosta);

        JMenuItem lopeta = new JMenuItem("Lopeta");
        lopeta.setMnemonic('L');
        lopeta.addActionListener(this);
        tiedostoMenu.add(lopeta);

        this.add(tiedostoMenu);
    }

    private void luoLisaaMenu() {
        JMenu lisaaMenu = new JMenu("Lisää");
        lisaaMenu.setMnemonic('L');

        JMenuItem tunnus = new JMenuItem("Uusi käyttäjätunnus/oppilas...");
        tunnus.setMnemonic('o');
        tunnus.addActionListener(this);
        lisaaMenu.add(tunnus);

        JMenuItem ryhma = new JMenuItem("Uusi ryhmä");
        ryhma.setMnemonic('r');
        ryhma.addActionListener(this);
        lisaaMenu.add(ryhma);

        JMenuItem kurssi = new JMenuItem("Uusi kurssi");
        kurssi.setMnemonic('k');
        kurssi.addActionListener(this);
        lisaaMenu.add(kurssi);

        this.add(lisaaMenu);
    }

    private void luoNaytaMenu() {
        JMenu naytaMenu = new JMenu("Näytä");
        naytaMenu.setMnemonic('N');

        JMenuItem oppilas = new JMenuItem("Näytä oppilas...");
        oppilas.setMnemonic('o');
        oppilas.addActionListener(this);
        naytaMenu.add(oppilas);

        JMenuItem ryhma = new JMenuItem("Näytä ryhmä...");
        ryhma.setMnemonic('r');
        ryhma.addActionListener(this);
        naytaMenu.add(ryhma);

        JMenuItem lukkari = new JMenuItem("Etsi lukujärjestys...");
        lukkari.setMnemonic('l');
        lukkari.addActionListener(this);
        naytaMenu.add(lukkari);

        this.add(naytaMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Tulosta lukkari")) {
            //TODO TULOSTA LUKKARI PRINTTERILLÄ
        } else if (e.getActionCommand().equals("Lopeta")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Uusi käyttäjätunnus/oppilas...")) {
            paaikkuna.vaihdaPaneeli(new LisaaKayttaja(paaikkuna));
        } else if (e.getActionCommand().equals("Uusi ryhmä")) {
            paaikkuna.vaihdaPaneeli(new LisaaRyhma(paaikkuna));
            System.out.println("ass");
        } else if (e.getActionCommand().equals("Uusi kurssi")) {
            paaikkuna.vaihdaPaneeli(new LisaaKurssi());
        } else if (e.getActionCommand().equals("Näytä oppilas...")) {
            paaikkuna.vaihdaPaneeli(new EtsiOppilas());
        } else if (e.getActionCommand().equals("Näytä ryhmä...")) {
//            paaikkuna.vaihdaPaneeli(new EtsiRyhma());
        } else if (e.getActionCommand().equals("Etsi lukujärjestys...")) {
//            paaikkuna.vaihdaPaneeli(new EtsiLukkari());
        }
    }
}
