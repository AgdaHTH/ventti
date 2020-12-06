package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Vinkki;
import java.io.IOException;
import java.util.List;

public interface VinkkiDao {

    boolean lisaaVinkki(Vinkki vinkki);
    boolean poistaVinkki(int indeksi);
    boolean poistaVinkki(Vinkki vinkki);
    boolean talletaVinkit();
    List<Vinkki> getKirjat();
    List<Vinkki> getBlogit();
    List<Vinkki> getPodcastit();
    List<Vinkki> haeKaikki() throws IOException;
}
