/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.ui;

import Lukuvinkkiohjelma.dao.VinkkiDao;
import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import Lukuvinkkiohjelma.domain.Sovelluslogiikka;
import Lukuvinkkiohjelma.domain.Vinkki;
import Lukuvinkkiohjelma.io.IO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mari
 */
public class Tekstikayttoliittyma {

    private IO io;
    private VinkkiDao dao;
    private Sovelluslogiikka sovelluslogiikka;

    public Tekstikayttoliittyma(IO io, VinkkiDao dao) { // tarvitseeko parametrina tiedostoa? annettava dao:lle jo aiemmin
        this.io = io;
        this.dao = dao;
        this.sovelluslogiikka = new Sovelluslogiikka(dao);
    }

    public void kaynnista() {
        io.print("Tervetuloa hallinnoimaan lukuvinkkejä!");
        io.print("");

        boolean kaynnissa = true;

        while (kaynnissa) {

            io.print("Komennot:");
            io.print("1 - Lisää vinkki");
            io.print("2 - Listaa vinkit");
            io.print("3 - Poista vinkki");
            io.print("0 - Sulje ohjelma");
            io.print("");

            String komento = io.readLine("Anna komento");
            io.print("");

            if (komento.equals("1")) {
                String otsikko = io.readLine("Anna otsikko:");
                String tyyppi = io.readLine("Anna tyyppi:");
                io.print("");

                if (sovelluslogiikka.lisaaVinkki(new Vinkki(otsikko, tyyppi))) {
                    io.print("Vinkki lisätty onnistuneesti!\n");
                } else {
                    io.print("Jotain meni pieleen vinkin lisäämisessä\n");
                }

            } else if (komento.equals("2")) {
                List<Vinkki> vinkkilista = sovelluslogiikka.listaaVinkit();

                for (Vinkki vinkki : vinkkilista) {
                    io.print(vinkki.toString());
                }

            } else if (komento.equals("3")) {
                List<Vinkki> vinkkilista = sovelluslogiikka.listaaVinkit();

                for (int i = 0; i < vinkkilista.size(); i++) {
                    io.print(i + " " + vinkkilista.get(i));
                }

                io.print("Anna poistettavan vinkin numero:");
                int numero = io.readInt("Anna poistettavan vinkin numero:");

                if (sovelluslogiikka.poistaVinkki(vinkkilista.get(numero))) {
                    io.print("Vinkki poistettu onnistuneesti!");
                } else {
                    io.print("Jotain meni pieleen vinkin lisäämisessä.");
                }

            } else if (komento.equals("0")) {
                io.print("Kiitos käynnistä! Hei hei!");
                kaynnissa = false;
            }
        }
    }
}
