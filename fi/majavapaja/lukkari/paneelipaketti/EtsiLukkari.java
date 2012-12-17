/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import fi.majavapaja.lukkari.Database;
import fi.majavapaja.lukkari.Paaikkuna;
import fi.majavapaja.lukkari.Ryhma;

/**
 * EtsiLukkari paneelissa etsitään ryhmän lukujärjestys
 * muokkaamista varten.
 *
 * @author Majavapaja
 */
@SuppressWarnings("serial")
public class EtsiLukkari extends javax.swing.JPanel {
	private Paaikkuna ikkuna;
    
	/**
	 * Luo lukkarinhakunäkymän.
	 * 
	 * @param ikkuna Paaikkuna, johon paneeli sijoitetaan
	 */
    public EtsiLukkari(Paaikkuna ikkuna) {
    	this.ikkuna = ikkuna;
    	
		
        initComponents();
        
        JButton btnMuokkaaLukkaria = new JButton("Muokkaa ryhmän lukujärjestystä");
        btnMuokkaaLukkaria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		muokkaaActionPerformed();
        	}
        });
        
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
        
        ryhmanNimiField = new JTextField();
        ryhmanNimiField.setColumns(10);
        ryhmanNimiField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					etsiActionPerformed();
				}
			}
		});
        
        JLabel lblRyhmanNimi = new JLabel("Ryhmän nimi");
        lblRyhmanNimi.setLabelFor(ryhmanNimiField);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGap(249)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnEtsi, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnTakaisin, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
        				.addComponent(btnMuokkaaLukkaria, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jScrollPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblRyhmanNimi)
        					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
        					.addComponent(ryhmanNimiField, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)))
        			.addGap(275))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(56)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(ryhmanNimiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblRyhmanNimi))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnEtsi)
        				.addComponent(btnTakaisin))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnMuokkaaLukkaria)
        			.addContainerGap(69, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
        
		// Hae kaikki ryhmät listaan
		etsiActionPerformed();
    }

    /**
     * Vaihtaa näkymäksi uuden AlmightyLukkariPaneeli paneelin,
     * jos hakutuloksista on valittuna ryhmä.
     */
    protected void muokkaaActionPerformed() {
		int i = ryhmaList.getSelectedIndex();
		if (i == -1)
			return;
		Ryhma ryhma = (Ryhma) ryhmaList.getModel().getElementAt(i);
		ikkuna.vaihdaPaneeli(new AlmightyLukkariPaneeli(ryhma));
	}

    /**
     * Pyytää pääikkunaa palaamaan edelliseen paneeliin.
     */
	protected void takaisinActionPerformed() {
		ikkuna.edellinenPaneeli();
	}

	/**
	 * Hakee tietokannasta syötettyä nimeä vastaavat ryhmät ja
	 * lisää ne hakutulosten listaan.
	 */
	protected void etsiActionPerformed() {
		String nimi = ryhmanNimiField.getText().trim();
		
		List<Ryhma> ryhmat = Database.haeRyhmat(nimi);
		
		if (ryhmat == null) {
			System.out.println("LOOOOL NULLL");
			return;
		}
		
		ryhmaList.setListData(ryhmat.toArray(new Ryhma[0]));
	}

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ryhmaList = new javax.swing.JList<Ryhma>();

        setPreferredSize(new java.awt.Dimension(800, 600));

        jScrollPane1.setViewportView(ryhmaList);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Ryhma> ryhmaList;
    private JTextField ryhmanNimiField;
    // End of variables declaration//GEN-END:variables
}
