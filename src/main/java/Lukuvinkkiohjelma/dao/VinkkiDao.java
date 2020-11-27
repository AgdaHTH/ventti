package Lukuvinkkiohjelma.dao;

import java.io.IOException;
import java.util.List;
import Lukuvinkkiohjelma.domain.Vinkki;

public interface VinkkiDao {

    boolean lisaaVinkki(Vinkki vinkki);
<<<<<<< HEAD

    boolean poistaVinkki(Vinkki vinkki);

=======
    boolean talletaVinkit(List<Vinkki> vinkit);
>>>>>>> cd9d3d17cdfb6a31aca0ea3625e5985f53f20151
    List<Vinkki> haeKaikki() throws IOException;
}
