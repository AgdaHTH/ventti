package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Vinkki;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VinkkiJsonDao implements VinkkiDao {
    
    private final File vinkkikirjasto;
    
    public VinkkiJsonDao() {
        vinkkikirjasto = new File("vinkkikirjasto.json");
    }

    @Override
    public boolean lisaaVinkki(Vinkki vinkki) {
        try {
            FileWriter writer = new FileWriter(vinkkikirjasto);
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(vinkki);
            System.out.println();
            gson.toJson(vinkki, writer);
            writer.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(VinkkiJsonDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Object> haeKaikki() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
