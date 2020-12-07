/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.domain;

import Lukuvinkkiohjelma.dao.VinkkiDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mari
 */
public class Sovelluslogiikka {

    // JSON-raakadatalista:
    private List<Vinkki> vinkkilista;
    private VinkkiDao dao;
    private Map<String, Boolean> listauksenParametrit;

    public Sovelluslogiikka(VinkkiDao vinkkiDao) {
        listauksenParametrit = new HashMap();
        listauksenParametrit.put("luettu", true);
        listauksenParametrit.put("lukematta", true);
        listauksenParametrit.put("kirja", true);
        listauksenParametrit.put("podcast", true);
        listauksenParametrit.put("blogi", true);
        
        this.dao = vinkkiDao;
        try {
            this.vinkkilista = dao.haeKaikki();
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
        }
    }
    
    public boolean muutaListauksenParametria(String parametri, boolean arvo) {
        if (listauksenParametrit.containsKey(parametri)) {
            listauksenParametrit.put(parametri, arvo);
            return true;
        }
        return false;
    }
    
    public boolean getListauksenParametri(String parametri) {
        if (listauksenParametrit.containsKey(parametri)) {
            return listauksenParametrit.get(parametri);
        }
        return false;
    }

    // Metodi lisää uuden vinkkiolion
    // - paikallisiin listoihin tyypeittäin
    // - ja toimittaa objectin daolle tallennettavaksi
    public boolean lisaaVinkki(Vinkki vinkki) {
        if (this.dao.lisaaVinkki(vinkki)) {
            try {
                this.vinkkilista = dao.haeKaikki();
                return true;
            } catch (Exception e) {
                System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
            }
        }
        return false;
    }

    public boolean poistaVinkki(int indeksi) {
        if (this.dao.poistaVinkki(indeksi)) {
            try {
                this.vinkkilista = dao.haeKaikki();
                return true;
            } catch (Exception e) {
                System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
            }
        }
        return false;
    }

    public List<Vinkki> listaaKirjat() {
        return dao.getKirjat();
    }

    public List<Vinkki> listaaBlogit() {
        return dao.getBlogit();
    }

    public List<Vinkki> listaaPodcastit() {
        return dao.getPodcastit();
    }
    
    public List<Vinkki> listaaKaikkiVinkit() {
        return this.vinkkilista;
    }
    
    public List<Vinkki> listaaParametrienMukaan() {
        List<Vinkki> rajattuLista = new ArrayList<>();
        
        for (Vinkki vinkki : vinkkilista) {
            if (vinkki.getLuettu() == listauksenParametrit.get("luettu")
                    || vinkki.getLuettu() != listauksenParametrit.get("lukematta")
                    && listauksenParametrit.get(vinkki.getTyyppi())) {
                rajattuLista.add(vinkki);
            }
        }
        return rajattuLista;
    }
}
