package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Blogi;
import Lukuvinkkiohjelma.domain.Kirja;
import Lukuvinkkiohjelma.domain.Podcast;
import Lukuvinkkiohjelma.domain.Vinkki;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
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
    private List<Vinkki> kirjat;
    private List<Vinkki> blogit;
    private List<Vinkki> podcastit;

    public VinkkiJsonDao(String tiedosto) {
        vinkkikirjasto = new File(tiedosto);
        kirjat = new ArrayList<>();
        blogit = new ArrayList<>();
        podcastit = new ArrayList<>();
        try {
            if (!vinkkikirjasto.exists()) {
                vinkkikirjasto.createNewFile();
//                testiTiedosto();
            }
            if (vinkkikirjasto.length() == 0L) {
                alustaTyhjaTiedosto();
            }
            puraTiedosto();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param vinkki - tiedostoon lis�tt�v� vinkki
     * @return palauttaa true, jos tiedoston muuttaminen on onnistunt.
     */
    @Override
    public boolean lisaaVinkki(Vinkki vinkki) {
        if (vinkki instanceof Kirja) {
            this.kirjat.add(vinkki);
        } else if (vinkki instanceof Podcast) {
            this.podcastit.add(vinkki);
        } else if (vinkki instanceof Blogi) {
            this.blogit.add(vinkki);
        }

        return talletaVinkit();
    }

    /**
     * Jos vinkkej� on k�sitelty yksitt�isin� listoina, indeksi
     * lasketaan suhteessa kaikkiin listoihin; kirjat, blogit, podcastit.
     *
     * Esimerkki: Jos vinkki on listan blogit indeksiss� 3, annetaan
     * parametriksi (kirjat.size() + 3)
     *
     * @param indeksi vinkin indeksi
     * @return palauttaa true, jos tiedoston muuttaminen on onnistunt.
     */
    @Override
    public boolean poistaVinkki(int indeksi) {
        if (0 <= indeksi && indeksi < kirjat.size()) {
            kirjat.remove(indeksi);
        } else if (indeksi < kirjat.size() + blogit.size()) {
            blogit.remove(indeksi - kirjat.size());
        } else if (indeksi < kirjat.size() + blogit.size() + podcastit.size()) {
            podcastit.remove(indeksi - (kirjat.size() + blogit.size()));
        }

        return talletaVinkit();
    }

    @Override
    public List<Vinkki> getKirjat() {
        return kirjat;
    }

    @Override
    public List<Vinkki> getBlogit() {
        return blogit;
    }

    @Override
    public List<Vinkki> getPodcastit() {
        return podcastit;
    }

    /**
     *
     * @return palauttaa kaikki vinkit yhten� listana
     * @throws IOException aiheutuu ep�onnistuneesta tiedoston
     * lukemisyrityksest�
     */
    @Override
    public List<Vinkki> haeKaikki() {
        puraTiedosto();

        List<Vinkki> vinkit = new ArrayList<>();

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

    /**
     * Listat t�rke�� tallentaa j�rjestyksess�; kirjat, blogit,
     * podcastit Muussa tapauksessa metodin puraTiedosto()
     * parsimisj�rjestyst� my�s muutettava.
     */
    @Override
    public boolean talletaVinkit() {
        List<List<Vinkki>> vinkit = new ArrayList<>();

        vinkit.add(this.kirjat);
        vinkit.add(this.blogit);
        vinkit.add(this.podcastit);

        try {
            FileWriter writer = new FileWriter(vinkkikirjasto, false);
            Gson gson = new GsonBuilder().create();

            gson.toJson(vinkit, writer);
            writer.close();

            return true;
        } catch (IOException ex) {
            Logger.getLogger(VinkkiJsonDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Alustaa tiedoston tyhjill� listoilla. Yksi jokaista vinkkityyppi�
     * kohden.
     */
    private void alustaTyhjaTiedosto() {
        List<Vinkki> k = new ArrayList<>();
        List<Vinkki> b = new ArrayList<>();
        List<Vinkki> p = new ArrayList<>();

        List<List<Vinkki>> vinkit = new ArrayList<>();

        vinkit.add(k);
        vinkit.add(b);
        vinkit.add(p);

        try {
            FileWriter writer = new FileWriter(vinkkikirjasto, false);
            Gson gson = new GsonBuilder().create();

            gson.toJson(vinkit, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Hakee datan tiedostosta ja parsii sen erillisille Vinkki-listoille.
     */
    private void puraTiedosto() {
        Gson gson = new Gson();

        List<List<Vinkki>> listat = new ArrayList<>();
        
        try {
            FileInputStream fileInputStream = new FileInputStream((File) vinkkikirjasto);
            InputStreamReader fileReader = new InputStreamReader(fileInputStream, "UTF-8");
            JsonReader jsonReader = new JsonReader(fileReader);

            listat = gson.fromJson(jsonReader, ArrayList.class);

            fileReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
        }

        kirjat = new ArrayList<>();
        List<Vinkki> raakalista = gson.fromJson(gson.toJson(listat.get(0)), ArrayList.class);
        for (Object vinkki : raakalista) {
            this.kirjat.add(gson.fromJson(gson.toJson(vinkki), Kirja.class));
        }

        blogit = new ArrayList<>();
        raakalista = gson.fromJson(gson.toJson(listat.get(1)), ArrayList.class);
        for (Object vinkki : raakalista) {
            this.blogit.add(gson.fromJson(gson.toJson(vinkki), Blogi.class));
        }

        podcastit = new ArrayList<>();
        raakalista = gson.fromJson(gson.toJson(listat.get(2)), ArrayList.class);
        for (Object vinkki : raakalista) {
            this.podcastit.add(gson.fromJson(gson.toJson(vinkki), Podcast.class));
        }
    }

    /**
     * Kirjoittaa tiedostoon testidataa.
     */
//    private void testiTiedosto() {
//        List<Vinkki> kirjat = new ArrayList<>();
//        kirjat.add(new Kirja("Kirja1", "Kirjoittaja1", "1"));
//        kirjat.add(new Kirja("Kirja2", "Kirjoittaja2", "2"));
//        List<Vinkki> blogit = new ArrayList<>();
//        blogit.add(new Blogi("Blogi1", "Kirjoittaja3", "www.1.fi"));
//        blogit.add(new Blogi("Blogi2", "Kirjoittaja4", "www.2.fi"));
//        List<Vinkki> podcastit = new ArrayList<>();
//        podcastit.add(new Podcast("Podcast1", "www.3.fi"));
//        podcastit.add(new Podcast("Podcast2", "www.4.fi"));
//        List<List<Vinkki>> vinkit = new ArrayList<>();
//        vinkit.add(kirjat);
//        vinkit.add(blogit);
//        vinkit.add(podcastit);
//
//        try {
//            FileWriter writer = new FileWriter(vinkkikirjasto, false);
//            Gson gson = new GsonBuilder().create();
//
//            gson.toJson(vinkit, writer);
//            writer.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
