package Lukuvinkkiohjelma.dao;

import java.util.ArrayList;
import java.util.List;

public class StubiDao implements VinkkiDao {

    private List<Object> vinkkikirjasto;

    public StubiDao() {
        vinkkikirjasto = new ArrayList<>();
//        vinkkikirjasto.add(new Vinkki("Testi", "Testi"));
//        vinkkikirjasto.add(new Vinkki("Testi2", "Testi2"));
//        vinkkikirjasto.add(new Vinkki("Erkki Esimerkki", "Erkki Esimerkki"));
    }

    @Override
    public boolean lisaaVinkki(Object vinkki) {

        
        
        if (vinkkikirjasto.size() >= 1) {

            if (vinkkikirjasto.contains(vinkki)) {
                return false;
            }
            vinkkikirjasto.add(vinkki);
            return true;            
        } else {            
            vinkkikirjasto = new ArrayList<>();
            vinkkikirjasto.add(vinkki);
            return true;
        }
    }

    @Override
    public boolean poistaVinkki(Object vinkki) {
        return false;
    }

    @Override
    public List<Object> haeKaikki() {        
        if (this.vinkkikirjasto == null) {
            return new ArrayList<>();
        }        

        if (!this.vinkkikirjasto.isEmpty()) {
            return this.vinkkikirjasto;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean talletaVinkit(List<Object> vinkit) {
        vinkkikirjasto = vinkit;
        return true;
    }

}
