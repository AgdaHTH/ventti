/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari
 */
public class Sovelluslogiikka {

    private List<Vinkki> vinkkilista;

    public Sovelluslogiikka() {
        this.vinkkilista = new ArrayList<>();
    }

    public void lisaaVinkki(Vinkki vinkki) {
        this.vinkkilista.add(vinkki);
    }

    public int testiMetodi() {
        return 100;
    }
}
