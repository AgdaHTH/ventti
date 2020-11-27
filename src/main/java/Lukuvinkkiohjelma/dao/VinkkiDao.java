package Lukuvinkkiohjelma.dao;

import java.io.IOException;
import java.util.List;
import Lukuvinkkiohjelma.domain.Vinkki;

public interface VinkkiDao {
    boolean lisaaVinkki(Vinkki vinkki);
    boolean talletaVinkit(List<Vinkki> vinkit);
    List<Vinkki> haeKaikki() throws IOException;
}
