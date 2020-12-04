package Lukuvinkkiohjelma.domain;

public class Kirja extends Vinkki {
    
    private String kirjoittaja;
    private String isbn;

    public Kirja(String otsikko, String kirjoittaja, String ISBN) {
        super("kirja", otsikko);
        this.kirjoittaja = kirjoittaja;
        this.isbn = ISBN;
    }

    public void setKirjoittaja(String uusiKirjoittaja) {
        if (!(uusiKirjoittaja.equals(""))) {
            this.kirjoittaja = uusiKirjoittaja;
        }
    }

    public void setISBN(String uusiISBN) {
        if (!(uusiISBN.equals(""))) {
            this.isbn = uusiISBN;
        }
    }

    public void setOtsikko(String uusiOtsikko) {
        if (!(uusiOtsikko.equals(""))) {
            this.otsikko = uusiOtsikko;
        }
    }

    @Override
    public String toString() {
        
        String luettuSuomeksi = "";
        
        if (this.luettu) {
            luettuSuomeksi = "Kyllä";
        } else {
            luettuSuomeksi = "Ei";
        }
        
        return "Tyyppi: " + tyyppi + "\nOtsikko: " + otsikko + "\nKirjoittaja: "
                + kirjoittaja + "\nISBN: " + isbn + "\nLuettu: " + luettuSuomeksi+"\n";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Kirja)) {
            return false;
        }
        if (object == this) {
            return true;
        }
        
        Kirja kirja = (Kirja) object;
        
        boolean otsikotSamat = this.otsikko.equals(kirja.getOtsikko());
        boolean kirjoittajatSamat = this.kirjoittaja.equals(kirja.getKirjoittaja());
        boolean ISBNSamat = this.isbn.equals(kirja.getISBN());
        return (otsikotSamat && kirjoittajatSamat && ISBNSamat);
    }

    public int hashCode() { // yksinkertainen versio

        int hashCodeSumma = 0;

        hashCodeSumma += this.otsikko.hashCode() + this.kirjoittaja.hashCode();
        hashCodeSumma += this.isbn.hashCode();

        return hashCodeSumma;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public String getISBN() {
        return isbn;
    }

}
