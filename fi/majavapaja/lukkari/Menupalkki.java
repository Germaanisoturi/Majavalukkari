package fi.majavapaja.lukkari;

import fi.majavapaja.lukkari.paneelipaketti.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Menupalkissa tapahtuu näkymien valinta ja luonti.
 * Menupalkin sisältö määräytyy käyttäjän oikeuksien mukaan.
 * 
 * @author s1001069
 */
@SuppressWarnings("serial")
public class Menupalkki extends JMenuBar implements ActionListener {

    private Paaikkuna ikkuna;
    
    /**
     * Luo menupalkin käyttäjän tunnuksien mukaan.
     * 
     * @param ikkuna pääikkuna, johon menupalkki sijoitetaan.
     * @param ktunnus sisäänkirjautuneen käyttäjän tunnukset.
     */
    public Menupalkki(Paaikkuna ikkuna, Kayttajatunnus ktunnus) {
        this.ikkuna = ikkuna;
        if (ktunnus.getOppilas() == null) {
            luoTiedostoMenu();
            luoLisaaMenu();
            luoNaytaMenu();
        } else {
            luoTiedostoMenu();
        }
    }

    private void luoTiedostoMenu() {
        JMenu tiedostoMenu = new JMenu("Tiedosto");
        tiedostoMenu.setMnemonic('T');

        JMenuItem tulosta = new JMenuItem("Tulosta lukkari");
        tulosta.setMnemonic('T');
        tulosta.addActionListener(this);
        tiedostoMenu.add(tulosta);

        JMenuItem kirjaaulos = new JMenuItem("Kirjaudu ulos");
        kirjaaulos.setMnemonic('K');
        kirjaaulos.addActionListener(this);
        tiedostoMenu.add(kirjaaulos);

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
        JMenu naytaMenu = new JMenu("Muokkaa");
        naytaMenu.setMnemonic('M');

        JMenuItem oppilas = new JMenuItem("Hallinnoi oppilaita/käyttäjiä");
        oppilas.setMnemonic('o');
        oppilas.addActionListener(this);
        naytaMenu.add(oppilas);

        JMenuItem kurssi = new JMenuItem("Hallinnoi kursseja");
        kurssi.setMnemonic('k');
        kurssi.addActionListener(this);
        naytaMenu.add(kurssi);

        JMenuItem ryhma = new JMenuItem("Hallinnoi ryhmiä");
        ryhma.setMnemonic('r');
        ryhma.addActionListener(this);
        naytaMenu.add(ryhma);

        JMenuItem lukkari = new JMenuItem("Hallinnoi lukujärjestyksiä");
        lukkari.setMnemonic('l');
        lukkari.addActionListener(this);
        naytaMenu.add(lukkari);

        this.add(naytaMenu);
    }
    
    /**
     * Vaihtaa pääikkunan näkymän valikosta tehdyn kutsun mukaisesti.
     * 
     * @param e kutsuva komponentti
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Tulosta lukkari")) {
            //TODO TULOSTA LUKKARI PRINTTERILLÄ
        } else if (e.getActionCommand().equals("Lopeta")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Kirjaudu ulos")) {
            ikkuna.kirjaaUlos();
        } else if (e.getActionCommand().equals("Uusi käyttäjätunnus/oppilas...")) {
            ikkuna.vaihdaPaneeli(new LisaaKayttaja(ikkuna));
        } else if (e.getActionCommand().equals("Uusi ryhmä")) {
            ikkuna.vaihdaPaneeli(new LisaaRyhma(ikkuna));
        } else if (e.getActionCommand().equals("Uusi kurssi")) {
            ikkuna.vaihdaPaneeli(new LisaaKurssi(ikkuna));
        } else if (e.getActionCommand().equals("Hallinnoi oppilaita/käyttäjiä")) {
            ikkuna.vaihdaPaneeli(new EtsiOppilas(ikkuna));
        } else if (e.getActionCommand().equals("Hallinnoi kursseja")) {
            ikkuna.vaihdaPaneeli(new EtsiKurssi(ikkuna));
        } else if (e.getActionCommand().equals("Hallinnoi ryhmiä")) {
            ikkuna.vaihdaPaneeli(new EtsiRyhma(ikkuna));
        } else if (e.getActionCommand().equals("Hallinnoi lukujärjestyksiä")) {
            ikkuna.vaihdaPaneeli(new EtsiLukkari(ikkuna));
        }
    }
}
