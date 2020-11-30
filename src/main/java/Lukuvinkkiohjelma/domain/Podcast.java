package Lukuvinkkiohjelma.domain;

public class Podcast {
    
    private final String tyyppi = "podcast";
    private String otsikko;
    private String url;
    
    public Podcast(String otsikko, String url) {
        this.otsikko = otsikko;
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
        return "Tyyppi: " + tyyppi + "\nOtsikko: " + otsikko + "\nUrl: " 
                + url + "\n";
    }
    
}
