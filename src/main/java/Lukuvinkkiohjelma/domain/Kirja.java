package Lukuvinkkiohjelma.domain;

public class Kirja {
    
    private final String tyyppi = "kirja";
    private String otsikko;
    private String kirjoittaja;
    private String ISBN;
    
    public Kirja(String otsikko, String kirjoittaja, String ISBN) {
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.ISBN = ISBN;
    }
    
    public void setKirjoittaja(String uusiKirjoittaja) {
        if (!(uusiKirjoittaja.equals(""))) {
            this.kirjoittaja = uusiKirjoittaja;
        }
    }
    
    public void setISBN(String uusiISBN) {
        if (!(uusiISBN.equals(""))) {
            this.ISBN = uusiISBN;
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
                + kirjoittaja + "\nISBN: " + ISBN + "\n";
    }
    
    public boolean equals(Kirja obj) {
        if (!(obj instanceof Kirja))
            return false;	
        if (obj == this)
            return true;
        boolean otsikotSamat = this.otsikko.equals(((Kirja) obj).otsikko);
        boolean tyypitSamat = this.tyyppi.equals(((Kirja) obj).tyyppi);
        boolean ISBNSamat = this.ISBN.equals(((Kirja) obj).ISBN);
        return (otsikotSamat && tyypitSamat && ISBNSamat);
    }
    
    public int hashCode(){ // yksinkertainen versio
        
        int hashCodeSumma = 0;
        
        hashCodeSumma += this.otsikko.hashCode()+this.kirjoittaja.hashCode();
        hashCodeSumma += this.ISBN.hashCode();
        
        return hashCodeSumma;
        
        
    }
}
