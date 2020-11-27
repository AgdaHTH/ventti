package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Vinkki;
import java.util.ArrayList;
import java.util.List;


public class StubiDao implements VinkkiDao {

    private final ArrayList<Vinkki> vinkkikirjasto;

    public StubiDao() {
        vinkkikirjasto = new ArrayList<>();

        vinkkikirjasto.add(new Vinkki("Testi", "Testi"));
        vinkkikirjasto.add(new Vinkki("Testi2", "Testi2"));
        vinkkikirjasto.add(new Vinkki("Erkki Esimerkki", "Erkki Esimerkki"));
        
    }

    @Override
    public boolean lisaaVinkki(Vinkki vinkki) {
        List<Vinkki> vinkit = null;
        if (!vinkkikirjasto.isEmpty()) {

            vinkit = haeKaikki();

            if (!vinkit.contains(vinkki)) {
                vinkit.add(vinkki);
            }

        } else {
            vinkit = new ArrayList<>();
            vinkit.add(vinkki);
        }


    }

    @Override
    public List<Vinkki> haeKaikki() {
        
        ArrayList<Vinkki> vinkit = vinkkikirjasto;
        if(vinkit == null) {
            return new ArrayList<Vinkki>();
        }
        return vinkit;
    }

}
