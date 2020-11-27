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
            System.out.println("1 - Lisää vinkki");
            System.out.println("2 - Listaa vinkit");
            System.out.println("0 - Sulje ohjelma");
            System.out.println("");

            System.out.println("Anna komento:");
            String komento = lukija.nextLine();
            System.out.println("");

            if (komento.equals("1")) {
                System.out.println("Anna otsikko:");
                String otsikko = lukija.nextLine();
                System.out.println("Anna tyyppi:");
                String tyyppi = lukija.nextLine();
                System.out.println("");

                if (sovelluslogiikka.lisaaVinkki(new Vinkki(otsikko, tyyppi))) {
                    System.out.println("Vinkki lisätty onnistuneesti!\n");
                } else {
                    System.out.println("Jotain meni pieleen vinkin lisäämisessä\n");
                }
                
            } else if (komento.equals("2")) {
                sovelluslogiikka.listaaVinkit();
                
            } else if (komento.equals("0")) {
                System.out.println("Kiitos käynnistä! Hei hei!");
                kaynnissa = false;
            }
        }
    }
}
