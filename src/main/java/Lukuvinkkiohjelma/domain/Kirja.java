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
}
