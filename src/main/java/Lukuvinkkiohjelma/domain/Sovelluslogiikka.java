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

    public void lisaaVinkki(Vinkki vinkki) {
        this.vinkkilista.add(vinkki);
        this.dao.lisaaVinkki(vinkki);
    }
}
