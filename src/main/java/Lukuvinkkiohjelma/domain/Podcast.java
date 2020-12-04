package Lukuvinkkiohjelma.domain;

public class Podcast extends Vinkki {

    private String url;

    public Podcast(String otsikko, String url) {
        super("podcast", otsikko);
        this.url = url;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        
        String luettuSuomeksi = "";
        
        if (this.luettu) {
            luettuSuomeksi = "Kyllä";
        } else {
            luettuSuomeksi = "Ei";
        }
        
        return "Tyyppi: " + tyyppi + "\nOtsikko: " + otsikko + "\nUrl: "
                + url + "\nKuunneltu: " + luettuSuomeksi+"\n";
    }

    public boolean equals(Podcast podcast) {
        if (!(podcast instanceof Podcast)) {
            return false;
        }
        if (podcast == this) {
            return true;
        }
        boolean otsikotSamat = this.otsikko.equals(podcast.getOtsikko());
        boolean tyypitSamat = this.tyyppi.equals(podcast.getTyyppi());
        boolean URLSamat = this.url.equals(podcast.getUrl());
        return (otsikotSamat && tyypitSamat && URLSamat);
    }

    public int hashCode() { // yksinkertainen versio

        int hashCodeSumma = 0;

        hashCodeSumma += this.otsikko.hashCode();
        hashCodeSumma += this.url.hashCode();

        return hashCodeSumma;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getUrl() {
        return url;
    }
}
