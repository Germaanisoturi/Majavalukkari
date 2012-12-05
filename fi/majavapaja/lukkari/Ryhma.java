/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.majavapaja.lukkari;

/**
 *
 * @author s1001069
 */
public class Ryhma {
    private String nimi;
    private int id;
    
    public Ryhma(String nimi){
        this.nimi=nimi;
    }
    
    public Ryhma(String nimi, int id) {
    	this.nimi = nimi;
    	this.id = id;
	}

	public String getNimi(){
        return nimi;
    }
    
    public int getId(){
        return id;
    }
    
    public String toString() {
    	return nimi;
    }
}
