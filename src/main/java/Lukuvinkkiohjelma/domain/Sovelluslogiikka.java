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
    public boolean lisaaVinkki(Vinkki vinkki) {
        this.vinkkilista.add(vinkki);
        return this.dao.lisaaVinkki(vinkki);
    }

    // Metodi listaa kaikki tallennetut vinkit tarkastelua varten
    public List<Vinkki> listaaVinkit() {
        return vinkkilista;
    }

    public boolean poistaVinkki(Vinkki vinkki) {
        this.vinkkilista.remove(vinkki);
        return this.dao.poistaVinkki(vinkki);
    }
}
