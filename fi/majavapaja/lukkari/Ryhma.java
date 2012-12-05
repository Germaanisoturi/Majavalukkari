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
    
    public Ryhma(int id, String nimi) {
    	this.id = id;
    	this.nimi = nimi;
	}

	public String getNimi(){
        return nimi;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
}
