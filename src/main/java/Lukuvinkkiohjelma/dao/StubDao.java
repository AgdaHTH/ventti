package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Blogi;
import Lukuvinkkiohjelma.domain.Kirja;
import Lukuvinkkiohjelma.domain.Podcast;
import Lukuvinkkiohjelma.domain.Vinkki;
import java.util.ArrayList;
import java.util.List;

public class StubDao implements VinkkiDao {

    private List<Vinkki> kirjat;
    private List<Vinkki> blogit;
    private List<Vinkki> podcastit;

    public StubDao() {
        kirjat = new ArrayList<>();
        blogit = new ArrayList<>();
        podcastit = new ArrayList<>();
    }

    @Override
    public boolean lisaaVinkki(Vinkki vinkki) {
        if (vinkki instanceof Kirja) {
            kirjat.add(vinkki);
        } else if (vinkki instanceof Blogi) {
            blogit.add(vinkki);
        } else if (vinkki instanceof Podcast) {
            podcastit.add(vinkki);
        }
        return true;
    }

    @Override
    public boolean poistaVinkki(int indeksi) {
        if (0 <= indeksi && indeksi < kirjat.size()) {
            kirjat.remove(indeksi);
        } else if (indeksi < kirjat.size() + blogit.size()) {
            blogit.remove(indeksi - kirjat.size());
        } else if (indeksi < kirjat.size() + blogit.size() + podcastit.size()) {
            podcastit.remove(indeksi - (kirjat.size() + blogit.size()));
        }
        return true;
    }
    

    @Override
    public List<Vinkki> haeKaikki() {
        ArrayList<Vinkki> vinkit = new ArrayList<>();
        for (Vinkki vinkki : kirjat) {
            vinkit.add(vinkki);
        }
        for (Vinkki vinkki : blogit) {
            vinkit.add(vinkki);
        }
        for (Vinkki vinkki : podcastit) {
            vinkit.add(vinkki);
        }
        return vinkit;
    }

    @Override
    public boolean talletaVinkit() {
        return true;
    }

    @Override
    public List<Vinkki> getKirjat() {
        return this.kirjat;
    }

    @Override
    public List<Vinkki> getBlogit() {
        return this.blogit;
    }

    @Override
    public List<Vinkki> getPodcastit() {
        return this.podcastit;
    }

}
