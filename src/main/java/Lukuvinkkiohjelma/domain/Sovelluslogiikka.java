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

    private VinkkiDao dao;
    private Map<String, Boolean> listauksenParametrit;

    public Sovelluslogiikka(VinkkiDao vinkkiDao) {
        listauksenParametrit = new HashMap();
        kaikkiParametritPaalle();

        this.dao = vinkkiDao;
    }

    public void kaikkiParametritPaalle() {
        listauksenParametrit = new HashMap();
        listauksenParametrit.put("luettu", true);
        listauksenParametrit.put("lukematta", true);
        listauksenParametrit.put("kirja", true);
        listauksenParametrit.put("podcast", true);
        listauksenParametrit.put("blogi", true);
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
        return this.dao.lisaaVinkki(vinkki);
    }

    public boolean poistaVinkki(int indeksi) {
        try {
            return this.dao.poistaVinkki(indeksi);
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
            return false;
        }
    }
    
    public Vinkki haeVinkki(int indeksi) {
        return dao.haeVinkki(indeksi);
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
        try {
            return dao.haeKaikki();
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
            return new ArrayList<>();
        }
    }

    public List<Vinkki> listaaParametrienMukaan() {
        List<Vinkki> rajattuLista = new ArrayList<>();

        try {
            for (Vinkki vinkki : this.dao.haeKaikki()) {
                if ((vinkki.getLuettu() == listauksenParametrit.get("luettu") && listauksenParametrit.get(vinkki.getTyyppi()))
                    || (vinkki.getLuettu() != listauksenParametrit.get("lukematta") && listauksenParametrit.get(vinkki.getTyyppi()))) {
                    rajattuLista.add(vinkki);
                }
            }
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
            return new ArrayList<>();
        }
        return rajattuLista;
    }
}
