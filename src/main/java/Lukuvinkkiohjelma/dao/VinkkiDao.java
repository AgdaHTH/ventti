package Lukuvinkkiohjelma.dao;

import java.io.IOException;
import java.util.List;
import Lukuvinkkiohjelma.domain.Vinkki;

public interface VinkkiDao {

    boolean lisaaVinkki(Vinkki vinkki);

    boolean poistaVinkki(Vinkki vinkki);

    List<Vinkki> haeKaikki() throws IOException;
}
