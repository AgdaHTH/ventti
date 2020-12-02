/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.domain;

import Lukuvinkkiohjelma.dao.VinkkiDao;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari
 */
public class Sovelluslogiikka {

    // JSON-raakadatalista:
    private List<Vinkki> vinkkilista;

    private List<Kirja> kirjalista;
    private List<Podcast> podcastlista;
    private List<Blogi> blogilista;

    private VinkkiDao dao;

    public Sovelluslogiikka(VinkkiDao vinkkiDao) {
        this.dao = vinkkiDao;
        try {
            this.vinkkilista = dao.haeKaikki();
            this.kirjalista = new ArrayList<>();
            this.podcastlista = new ArrayList<>();
            this.blogilista = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
        }
    }

    // Metodi lisää uuden vinkkiolion
    // - paikallisiin listoihin tyypeittäin
    // - ja toimittaa objectin daolle tallennettavaksi
    public boolean lisaaVinkki(Vinkki vinkki) {
        if (vinkki instanceof Kirja) {
            this.kirjalista.add((Kirja) vinkki);
        } else if (vinkki instanceof Podcast) {
            this.podcastlista.add((Podcast) vinkki);
        } else if (vinkki instanceof Blogi) {
            this.blogilista.add((Blogi) vinkki);
        }
        
        return this.dao.lisaaVinkki(vinkki);
    }

    public List<Kirja> listaaKirjat() {
        parsiVinkitListoille();
        return kirjalista;
    }

    public List<Podcast> listaaPodcastit() {
        parsiVinkitListoille();
        return podcastlista;
    }

    public List<Blogi> listaaBlogit() {
        parsiVinkitListoille();
        return blogilista;
    }

    // Metodi listaa kaikki tallennetut vinkit tarkastelua varten
    private boolean parsiVinkitListoille() {
        List<Object> poistettavatjsonit = new ArrayList<>();

        try {
            if (!vinkkilista.isEmpty()) {
                Gson gson = new Gson();

                for (Object jsonvinkki : vinkkilista) {
                    // fromJson aiheuttaa virheen, jos .toJsonia ei ole ja missä tahansa
                    // kentässä on välilyönti!
                    
                    if (jsonvinkki instanceof Kirja) {
                        Kirja kirja = gson.fromJson(gson.toJson(jsonvinkki), Kirja.class);
                        kirjalista.add(kirja);
                    } else if (jsonvinkki instanceof Podcast) {
                        Podcast podcast = gson.fromJson(gson.toJson(jsonvinkki), Podcast.class);
                        podcastlista.add(podcast);
                    } else if (jsonvinkki instanceof Blogi) {
                        Blogi blogi = gson.fromJson(gson.toJson(jsonvinkki), Blogi.class);
                        blogilista.add(blogi);
                    }
                    poistettavatjsonit.add(jsonvinkki);
                }

                vinkkilista.removeAll(poistettavatjsonit);
            }
        } catch (Exception e) {
            System.out.println("Listauksessa tapahtui virhe:\n");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Tällä hetkellä (29.11.) poistaa vain paikallisesta listasta.
    public boolean poistaVinkki(Vinkki vinkki) {
        if (vinkki instanceof Kirja) {
            kirjalista.remove((Kirja) vinkki);
        } else if (vinkki instanceof Podcast) {
            podcastlista.remove((Podcast) vinkki);
        } else if (vinkki instanceof Blogi) {
            blogilista.remove((Blogi) vinkki);
        }
        return this.dao.poistaVinkki(vinkki);
    }
}
