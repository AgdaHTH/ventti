package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Vinkki;
import java.util.ArrayList;
import java.util.List;

public class StubiDao implements VinkkiDao {

    private List<Vinkki> vinkkikirjasto;

    public StubiDao() {
        vinkkikirjasto = new ArrayList<>();
//        vinkkikirjasto.add(new Vinkki("Testi", "Testi"));
//        vinkkikirjasto.add(new Vinkki("Testi2", "Testi2"));
//        vinkkikirjasto.add(new Vinkki("Erkki Esimerkki", "Erkki Esimerkki"));
    }

    @Override
    public boolean lisaaVinkki(Vinkki vinkki) {

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
    public boolean poistaVinkki(int indeksi) {
        if (vinkkikirjasto.size() >= 1) {
            vinkkikirjasto.remove(indeksi);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean poistaVinkki(Vinkki vinkki) {
        if (vinkkikirjasto.contains(vinkki)) {
            vinkkikirjasto.remove(vinkki);
            return true;
        }
        return false;
    }

    @Override
    public List<Vinkki> haeKaikki() {
        if (this.vinkkikirjasto == null) {
            return new ArrayList<>();
        }

        if (!this.vinkkikirjasto.isEmpty()) {
            return this.vinkkikirjasto;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean talletaVinkit() {
        return true;
    }

    @Override
    public List<Vinkki> getKirjat() {
        return new ArrayList<>();
    }

    @Override
    public List<Vinkki> getBlogit() {
        return new ArrayList<>();
    }

    @Override
    public List<Vinkki> getPodcastit() {
        return new ArrayList<>();
    }

}
