package Lukuvinkkiohjelma.dao;

import java.io.IOException;
import java.util.List;

public interface VinkkiDao {

    boolean lisaaVinkki(Object vinkki);
    boolean poistaVinkki(Object vinkki);
    boolean talletaVinkit(List<Object> vinkit);
    List<Object> haeKaikki() throws IOException;
}
