package Lukuvinkkiohjelma.domain;

public abstract class Vinkki {

    protected final String tyyppi;
    protected String otsikko;

    public Vinkki(String tyyppi, String otsikko) {
        this.tyyppi = tyyppi;
        this.otsikko = otsikko;
    }

}
