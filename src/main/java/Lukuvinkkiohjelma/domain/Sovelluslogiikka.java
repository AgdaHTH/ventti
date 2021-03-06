/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.domain;

import Lukuvinkkiohjelma.dao.VinkkiDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        return this.dao.poistaVinkki(indeksi);
    }

    public boolean muokkaaVinkki(int indeksi, Vinkki vinkki) {
        if (poistaVinkki(indeksi)) {
            return (lisaaVinkki(vinkki));
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
        return dao.haeKaikki();
    }

    public List<Vinkki> listaaParametrienMukaan() {
        List<Vinkki> rajattuLista = new ArrayList<>();

        for (Vinkki vinkki : this.dao.haeKaikki()) {
            if (((listauksenParametrit.get("luettu") && (vinkki.getLuettu()))
                    || (listauksenParametrit.get("lukematta") && (!vinkki.getLuettu())))
                    && listauksenParametrit.get(vinkki.getTyyppi())) {
                rajattuLista.add(vinkki);
            }
        }
        Collections.sort(rajattuLista, new timeComparator());
        //Collections.sort(rajattuLista, new otsikkoComparator());

        return rajattuLista;
    }

    static class timeComparator implements Comparator<Vinkki> {

        @Override
        public int compare(Vinkki vinkki, Vinkki verrattava) {
            if (vinkki.getTimestamp() != null && verrattava.getTimestamp() != null) {
                return verrattava.getTimestamp().compareTo(vinkki.getTimestamp());
            } else {
                return 0;
            }
        }
    }

    /*
    static class otsikkoComparator implements Comparator<Vinkki> {
        @Override
        public int compare(Vinkki vinkki, Vinkki verrattava) {
            return vinkki.getOtsikko().compareToIgnoreCase(verrattava.getOtsikko());
        }
    }
     */
}
