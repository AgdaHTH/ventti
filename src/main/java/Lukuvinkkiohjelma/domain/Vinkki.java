package Lukuvinkkiohjelma.domain;

import java.sql.Timestamp;

public abstract class Vinkki {

    protected final String tyyppi;
    protected String otsikko;
    protected boolean luettu;
    protected Timestamp timestamp;

    public Vinkki(String tyyppi, String otsikko) {
        this.tyyppi = tyyppi;
        this.otsikko = otsikko;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public void toggleLuettu() {
        this.luettu = !this.luettu;

    }

    public boolean getLuettu() {
        return this.luettu;
    }

    public String getLuettuSuomeksi() {
        if (this.luettu) {
            return "Kyllä";
        } else {
            return "Ei";
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vinkki)) {
            return false;
        }
        if (object == this) {
            return true;
        }

        Vinkki vinkki = (Vinkki) object;
        boolean otsikotSamat = this.otsikko.equals(vinkki.getOtsikko());
        boolean tyypitSamat = this.tyyppi.equals(vinkki.getTyyppi());
        return (otsikotSamat && tyypitSamat);
    }

    @Override
    public int hashCode() { // yksinkertainen versio

        int hashCodeSumma = 0;

        hashCodeSumma += this.otsikko.hashCode() + this.tyyppi.hashCode();

        return hashCodeSumma;
    }

    public void setOtsikko(String otsikko) {
        if (!(otsikko.equals(""))) {
            this.otsikko = otsikko;
        }
    }

    public String getOtsikko() {
        return this.otsikko;
    }

    public String getTyyppi() {
        return this.tyyppi;
    }
    
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

}
