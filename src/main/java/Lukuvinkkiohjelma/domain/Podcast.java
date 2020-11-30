package Lukuvinkkiohjelma.domain;

public class Podcast extends Vinkki{
    
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
    
    public boolean equals(Podcast obj) {
        if (!(obj instanceof Podcast))
            return false;	
        if (obj == this)
            return true;
        boolean otsikotSamat = this.otsikko.equals(((Podcast) obj).otsikko);
        boolean tyypitSamat = this.tyyppi.equals(((Podcast) obj).tyyppi);
        boolean URLSamat = this.url.equals(((Podcast) obj).url);
        return (otsikotSamat && tyypitSamat && URLSamat);
    }
    
    public int hashCode(){ // yksinkertainen versio
        
        int hashCodeSumma = 0;
        
        hashCodeSumma += this.otsikko.hashCode();
        hashCodeSumma += this.url.hashCode();
        
        return hashCodeSumma;
        
        
    }
    
}
