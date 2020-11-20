package Lukuvinkkiohjelma.ui;

import java.io.IOException;
import java.util.List;

public interface VinkkiDao {
    boolean lisaaVinkki(Object vinkki);
    List<Object> haeKaikki() throws IOException;
}
