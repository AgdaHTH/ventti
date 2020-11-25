/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.domain;

/**
 *
 * @author mari
 */
public class Vinkki {

    private String otsikko;
    private String tyyppi;

    public Vinkki(String otsikko, String tyyppi) {
        this.otsikko = otsikko;
        this.tyyppi = tyyppi;
    }

    // Metodi vinkin muokkaustoiminnallisuutta varten
    public void setOtsikko(String uusiOtsikko) {
        this.otsikko = uusiOtsikko;
    }

    // Metodi vinkin muokkaustoiminnallisuutta varten
    public void setTyyppi(String uusiTyyppi) {
        this.tyyppi = uusiTyyppi;
    }

    // Palauttaa vinkin luettavaksi selkokielisenä. 
    @Override
    public String toString() {
        return "\t Otsikko: " + otsikko + "\n\t Tyyppi: " + tyyppi + "\n\n";
    }
}
