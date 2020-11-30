package Lukuvinkkiohjelma.domain;

/**
 *
 * @author Eemeli
 */
public class Blogi {
    
    private final String tyyppi = "blogi";
    private String otsikko;
    private String kirjoittaja;
    private String url;
    
    public Blogi(String otsikko, String kirjoittaja, String url) {
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.url = url;
    }
    
    public void setKirjoittaja(String uusiKirjoittaja) {
        if (!(uusiKirjoittaja.equals(""))) {
            this.kirjoittaja = uusiKirjoittaja;
        }
    }
    
    public void setISBN(String uusiISBN) {
        if (!(uusiISBN.equals(""))) {
            this.url = uusiISBN;
        }
    }

    public void setOtsikko(String uusiOtsikko) {
        if (!(uusiOtsikko.equals(""))) {
            this.otsikko = uusiOtsikko;
        }
    }
 
    @Override
    public String toString() {
        return "Tyyppi: " + tyyppi + "\nOtsikko: " + otsikko + "\nKirjoittaja: " 
                + kirjoittaja + "\nURL: " + url + "\n";
    }
    
    public boolean equals(Blogi obj) {
        if (!(obj instanceof Blogi))
            return false;	
        if (obj == this)
            return true;
        boolean otsikotSamat = this.otsikko.equals(((Blogi) obj).otsikko);
        boolean tyypitSamat = this.tyyppi.equals(((Blogi) obj).tyyppi);
        boolean URLSamat = this.url.equals(((Blogi) obj).url);
        return (otsikotSamat && tyypitSamat && URLSamat);
    }
    
    public int hashCode(){ // yksinkertainen versio
        
        int hashCodeSumma = 0;
        
        hashCodeSumma += this.otsikko.hashCode()+this.kirjoittaja.hashCode();
        hashCodeSumma += this.url.hashCode();
        
        return hashCodeSumma;
        
        
    }
}
