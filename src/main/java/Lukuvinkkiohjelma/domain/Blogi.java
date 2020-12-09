package Lukuvinkkiohjelma.domain;

/**
 *
 * @author Eemeli
 */
public class Blogi extends Vinkki {

    private String kirjoittaja;
    private String url;

    public Blogi(String otsikko, String kirjoittaja, String url) {
        super("blogi", otsikko);
        this.kirjoittaja = kirjoittaja;
        this.url = url;
    }

    public void setKirjoittaja(String uusiKirjoittaja) {
        if (!(uusiKirjoittaja.equals(""))) {
            this.kirjoittaja = uusiKirjoittaja;
        }
    }

    public void setUrl(String uusiUrl) {
        if (!(uusiUrl.equals(""))) {
            this.url = uusiUrl;
        }
    }

    @Override
    public String toString() {

        return "Tyyppi: " + tyyppi + "\nOtsikko: " + otsikko + "\nKirjoittaja: "
                + kirjoittaja + "\nURL: " + url + "\nLuettu: " + this.getLuettuSuomeksi() + "\n";
    }

    public boolean equals(Object object) {
        if (!(object instanceof Blogi)) {
            return false;
        }
        if (object == this) {
            return true;
        }

        Blogi blogi = (Blogi) object;

        boolean otsikotSamat = this.otsikko.equals(blogi.getOtsikko());
        boolean kirjoittajatSamat = this.kirjoittaja.equals(blogi.getKirjoittaja());
        boolean URLSamat = this.url.equals(blogi.getUrl());
        return (otsikotSamat && kirjoittajatSamat && URLSamat);
    }

    public int hashCode() { // yksinkertainen versio

        int hashCodeSumma = 0;

        hashCodeSumma += this.otsikko.hashCode() + this.kirjoittaja.hashCode();
        hashCodeSumma += this.url.hashCode();

        return hashCodeSumma;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public String getUrl() {
        return url;
    }
}
