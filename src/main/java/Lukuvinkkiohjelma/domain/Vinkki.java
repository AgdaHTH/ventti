package Lukuvinkkiohjelma.domain;

public abstract class Vinkki {

    protected final String tyyppi;
    protected String otsikko;
    protected boolean luettu;
    

    public Vinkki(String tyyppi, String otsikko) {
        this.tyyppi = tyyppi;
        this.otsikko = otsikko;

    }
    
    public void toggleLuettu() {
        this.luettu = !this.luettu;
        
    }
    
    public boolean getLuettu() {
        return this.luettu;
    }
    
    public String getLuettuSuomeksi() {
        if (this.luettu) {
            return "Kyll�";
        } else {
            return "Ei";
        }
    }
    

    
    /*
=======
    @Override
>>>>>>> f53f7c4cf6d9c32d817de9975b8b13e5ab61777c
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
    
    
    public String getOtsikko() {
        return this.otsikko;
    }
    
    public String getTyyppi() {
        return this.tyyppi;
    }

    */

}
