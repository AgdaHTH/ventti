package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Blogi;
import Lukuvinkkiohjelma.domain.Kirja;
import Lukuvinkkiohjelma.domain.Podcast;
import Lukuvinkkiohjelma.domain.Vinkki;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VinkkiJsonDao implements VinkkiDao {

    private final File vinkkikirjasto;

    public VinkkiJsonDao(String tiedosto) {
        vinkkikirjasto = new File(tiedosto);
        if (!vinkkikirjasto.exists()) {
            try {
                vinkkikirjasto.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

// Vinkin lisääminen Vinkki-muotoisena vinkkilistaan
    @Override
    public boolean lisaaVinkki(Vinkki vinkki) {
        List vinkit = new ArrayList();
        if (vinkkikirjasto.exists()) {
            try {
                vinkit = haeKaikki();

                if (!vinkit.contains(vinkki)) {
                    vinkit.add(vinkki);
                }
            } catch (IOException ex) {
                Logger.getLogger(VinkkiJsonDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            vinkit = new ArrayList<>();
            vinkit.add(vinkki);
        }

        return talletaVinkit(vinkit);
    }

    // TODO
    @Override
    public boolean poistaVinkki(Vinkki vinkki) {
        List vinkit = new ArrayList();
        if (vinkkikirjasto.exists()) {
            try {
                vinkit = haeKaikki();

                if (vinkit.contains(vinkki)) {
                    vinkit.remove(vinkki);
                }
            } catch (IOException ex) {
                Logger.getLogger(VinkkiJsonDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        return talletaVinkit(vinkit);

    }

    // Vinkkien hakeminen tiedostosta Vinkki-muodossa
    @Override
    public List<Vinkki> haeKaikki() throws IOException {
        InputStreamReader fileReader
                = new InputStreamReader(new FileInputStream(
                        (File) vinkkikirjasto), "UTF-8");

        JsonReader jsonReader = new JsonReader(fileReader);

        VinkkiDeserializer deserializer = new VinkkiDeserializer("tyyppi");
        deserializer.lisaaLuokka("kirja", Kirja.class);
        deserializer.lisaaLuokka("podcast", Podcast.class);
        deserializer.lisaaLuokka("blogi", Blogi.class);
        
        Gson gsonbuilder = new GsonBuilder()
                .registerTypeAdapter(Vinkki.class, deserializer)
                .create();
        
        List<Vinkki> vinkit = new ArrayList<>();

        Type typeToken = new TypeToken<List<Vinkki>>(){}.getType();
        vinkit = gsonbuilder.fromJson(jsonReader, typeToken);

        return vinkit;
    }

    // Vinkkien tallennus tiedostoon JSON-muodossa
    @Override
    public boolean talletaVinkit(List<Vinkki> vinkit) {
        try {
            FileWriter writer = new FileWriter(vinkkikirjasto);
            Gson gson = new GsonBuilder().create();

            gson.toJson(vinkit, writer);
            writer.close();

            return true;
        } catch (IOException ex) {
            Logger.getLogger(VinkkiJsonDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
