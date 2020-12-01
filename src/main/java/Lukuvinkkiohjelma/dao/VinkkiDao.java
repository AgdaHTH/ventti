package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Vinkki;
import java.io.IOException;
import java.util.List;

public interface VinkkiDao {

    boolean lisaaVinkki(Vinkki vinkki);
    boolean poistaVinkki(Vinkki vinkki);
    boolean talletaVinkit(List<Vinkki> vinkit);
    List<Vinkki> haeKaikki() throws IOException;
}
