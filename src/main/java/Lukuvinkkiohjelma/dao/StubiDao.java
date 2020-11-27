package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Vinkki;
import java.util.ArrayList;
import java.util.List;

public class StubiDao implements VinkkiDao {

    private List<Vinkki> vinkkikirjasto;

    public StubiDao() {
        vinkkikirjasto = new ArrayList<>();

        vinkkikirjasto.add(new Vinkki("Testi", "Testi"));
        vinkkikirjasto.add(new Vinkki("Testi2", "Testi2"));
        vinkkikirjasto.add(new Vinkki("Erkki Esimerkki", "Erkki Esimerkki"));

    }

    @Override
    public boolean lisaaVinkki(Vinkki vinkki) {
        List<Vinkki> vinkit = vinkkikirjasto;
        if (!vinkkikirjasto.isEmpty()) {

            vinkit = haeKaikki();

            if (!vinkit.contains(vinkki)) {
                vinkit.add(vinkki);
                return true;
            }

        } else {
            vinkit = new ArrayList<>();
            vinkit.add(vinkki);
            return true;
        }
        return false;

    }

    @Override
    public boolean poistaVinkki(Vinkki vinkki) {
        return false;
    }

    @Override
    public List<Vinkki> haeKaikki() {
<<<<<<< HEAD

        ArrayList<Vinkki> vinkit = vinkkikirjasto;
        if (vinkit == null) {
=======
        
        List<Vinkki> vinkit = vinkkikirjasto;
        if(vinkit == null) {
>>>>>>> cd9d3d17cdfb6a31aca0ea3625e5985f53f20151
            return new ArrayList<Vinkki>();
        }
        return vinkit;
    }

    @Override
    public boolean talletaVinkit(List<Vinkki> vinkit) {
        vinkkikirjasto = vinkit;
        return true;
    }

}
