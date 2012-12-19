package fi.majavapaja.lukkari;

import fi.majavapaja.lukkari.paneelipaketti.Kirjautuminen;
import java.awt.Dimension;
import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JPanel;

/**
 * Pääikkunassa näytetään ja selataan kaikkia ohjelman paneeleita. Pääikkunan näkymä määräytyy sisäänkirjautujan oikeuksien mukaan.
 * 
 * @author s1001069
 */
public class Paaikkuna extends javax.swing.JFrame {

	private Stack<JPanel> paneeliPino;

	/**
	 * Luo Majavalukkarin pääikkunan ja pakottaa ensimmäiseksi näkymäksi sisäänkirjautumisen.
	 */
	public Paaikkuna() {
		initComponents();
		setTitle("Majavalukkari");
		setResizable(false);
		paneeliPino = new Stack<JPanel>();
		vaihdaPaneeli(new Kirjautuminen(this));
	}

	/**
	 * Vaihtaa ja päivittää pääikkunassa näytettävän näkymän.
	 * 
	 * @param uusiPaneeli
	 *            näkymään avautuva paneeli.
	 */
	public void vaihdaPaneeli(JPanel uusiPaneeli) {
		paneeliPino.push(uusiPaneeli);
		setContentPane(uusiPaneeli);
		revalidate();
	}

	/**
	 * Poistaa nykyisen näkymän ja vaihtaa sen edelliseen.
	 */
	public void edellinenPaneeli() {
		try {
			paneeliPino.pop();
			JPanel ylinPaneeli = paneeliPino.peek();
			setContentPane(ylinPaneeli);
		} catch (EmptyStackException e) {
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(800, 600));
			setContentPane(p);
			validate();
		}
	}

	/**
	 * Kirjaa käyttäjän ulos ohjelmasta.
	 */
	public void kirjaaUlos() {
		paneeliPino.clear();
		vaihdaPaneeli(new Kirjautuminen(this));
		this.setJMenuBar(null);
		revalidate();
	}

	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 800, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 600, Short.MAX_VALUE));

		pack();
	}

	/**
	 * Ohjelman käynnistävä päämetodi.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Paaikkuna().setVisible(true);
			}
		});
	}
}
