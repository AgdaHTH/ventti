/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.domain;

import Lukuvinkkiohjelma.dao.VinkkiDao;
import java.util.List;

/**
 *
 * @author mari
 */
public class Sovelluslogiikka {

    // JSON-raakadatalista:
    private List<Vinkki> vinkkilista;
    private VinkkiDao dao;

    public Sovelluslogiikka(VinkkiDao vinkkiDao) {
        this.dao = vinkkiDao;
        try {
            this.vinkkilista = dao.haeKaikki();
        } catch (Exception e) {
            System.out.println("Vinkkien lataus epaonnistui!\n" + e.toString());
        }
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
}
