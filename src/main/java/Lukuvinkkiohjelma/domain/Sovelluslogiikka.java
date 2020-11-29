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
    private List<Object> vinkkilista;
    // Kirjavinkkilista:
    private List<Kirja> kirjalista;
    
    // private List<Podcast> podcastlista;
    // private List<Blogi> blogilista;
    
    private VinkkiDao dao;

    public Sovelluslogiikka(VinkkiDao vinkkiDao) {
        this.dao = vinkkiDao;
        try {
            this.vinkkilista = dao.haeKaikki();
            this.kirjalista = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
        }
    }

    // Metodi lisää uuden vinkkiolion
    // - paikallisiin listoihin tyypeittäin
    // - ja toimittaa objectin daolle tallennettavaksi
    public boolean lisaaVinkki(Object vinkki) {
        if (vinkki instanceof Kirja) {
            this.kirjalista.add((Kirja) vinkki);
        }
        
        // elseif instanceof podcast...
        
        // Daolle object-muodossa tallennettavaksi
        return this.dao.lisaaVinkki(vinkki);
    }

    // Metodi listaa kaikki tallennetut vinkit tarkastelua varten
    public List<Kirja> listaaKirjat() {
        List<Object> poistettavatjsonit = new ArrayList<>();
        
        if (!vinkkilista.isEmpty()) {
            Gson gson = new Gson();
            
            // Luupin tulee ottaa huomioon myös Podcast ja Blogityypit!
            for (Object jsonvinkki : vinkkilista) {
                // fromJson kaataa ohjelman, jos .toJsonia ei ole ja missä tahansa
                // kentässä on välilyönti!
                Kirja kirja = gson.fromJson(gson.toJson(jsonvinkki), Kirja.class);
                kirjalista.add(kirja);
                poistettavatjsonit.add(jsonvinkki);
            }
            
            vinkkilista.removeAll(poistettavatjsonit);
        }
        
        return kirjalista;
    }

    // Tällä hetkellä (29.11.) poistaa vain paikallisesta listasta.
    public boolean poistaVinkki(Object vinkki) {
        if (vinkki instanceof Kirja) {
            kirjalista.remove((Kirja) vinkki);
        }
        return this.dao.poistaVinkki(vinkki);
    }
}
