/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.ui;

import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import Lukuvinkkiohjelma.domain.Sovelluslogiikka;
import Lukuvinkkiohjelma.domain.Vinkki;
import java.util.Scanner;

/**
 *
 * @author mari
 */
public class Tekstikayttoliittyma {

    private Sovelluslogiikka sovelluslogiikka;
    private Scanner lukija;

    public Tekstikayttoliittyma() {
        this.sovelluslogiikka = new Sovelluslogiikka(new VinkkiJsonDao("vinkit"));
        this.lukija = new Scanner(System.in);
    }

    public void kaynnista() {
        System.out.println("Tervetuloa hallinnoimaan lukuvinkkejä!");
        System.out.println("");

        boolean kaynnissa = true;

        while (kaynnissa) {

            System.out.println("Komennot:");
            System.out.println("lisaa - Lisää vinkki");
            System.out.println("sulje - Sulje ohjelma");
            System.out.println("");

            System.out.println("Anna komento:");
            String komento = lukija.nextLine();
            System.out.println("");

            if (komento.equals("lisaa")) {
                System.out.println("Anna otsikko:");
                String otsikko = lukija.nextLine();
                System.out.println("Anna tyyppi:");
                String tyyppi = lukija.nextLine();
                System.out.println("");

                sovelluslogiikka.lisaaVinkki(new Vinkki(otsikko, tyyppi));
                
            } else if (komento.equals("sulje")) {
                System.out.println("Kiitos käynnistä! Hei hei!");
                kaynnissa = false;
            }

        }
    }
}
