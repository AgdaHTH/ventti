/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.ui;

import Lukuvinkkiohjelma.dao.VinkkiDao;
import Lukuvinkkiohjelma.domain.Kirja;
import Lukuvinkkiohjelma.domain.Sovelluslogiikka;
import Lukuvinkkiohjelma.io.IO;
import java.util.List;

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
            io.print("\nTyhjä syöte eli pelkkä enter keskeyttää toiminnon.\n");

            if (komento.equals("1")) {

                io.print("Millainen vinkki lisätään?\n\t1. Kirja\n\t2. Podcast\n\t3. Blogi\n");
                String vinkkityyppi = io.readLine("Anna komento");
                io.print("");

                if (keskeytetaan(vinkkityyppi)) {
                    break;
                }

                if (vinkkityyppi.equals("1")) {
                    String otsikko = io.readLine("Kirjan otsikko: ");
                    if (keskeytetaan(otsikko)) {
                        break;
                    }

                    String kirjoittaja = io.readLine("\nKirjan kirjoittaja: ");
                    if (keskeytetaan(kirjoittaja)) {
                        break;
                    }
                    String ISBN = io.readLine("\nKirjan ISBN: ");
                    if (keskeytetaan(ISBN)) {
                        break;
                    }

                    if (sovelluslogiikka.lisaaVinkki(new Kirja(otsikko, kirjoittaja, ISBN))) {
                        io.print("");
                        io.print("Uusi kirjavinkki lisatty onnistuneesti!");
                        io.print("");
                    } else {
                        io.print("\nJotain meni pieleen uuden kirjavinkin lisamisessa...\n");
                    }
                }

            } else if (komento.equals("2")) {
                List<Kirja> kirjalista = sovelluslogiikka.listaaKirjat();
                // Tähän myös podcastien ja blogien haku...

                if (!kirjalista.isEmpty()) {
                    for (Kirja kirja : kirjalista) {
                        io.print(kirja.toString());
                    }
                } else {
                    io.print("Ei vielä lisättyjä vinkkejä!\n");
                }

            } else if (komento.equals("3")) {
                List<Kirja> kirjalista = sovelluslogiikka.listaaKirjat();

                if (!kirjalista.isEmpty()) {
                    for (int i = 0; i < kirjalista.size(); i++) {
                        io.print(i + " " + kirjalista.get(i));
                    }
                }

                int numero = io.readInt("Anna poistettavan vinkin numero:");

                if (sovelluslogiikka.poistaVinkki(kirjalista.get(numero))) {
                    io.print("\nVinkki poistettu onnistuneesti!\n");
                } else {
                    io.print("\nJotain meni pieleen vinkin poistamisessa.\n");
                }

            } else if (komento.equals("0")) {
                io.print("Kiitos käynnistä! Hei hei!");
                kaynnissa = false;
            }
        }
    }

    private boolean keskeytetaan(String syote) {
        if (syote.equals("")) {
            io.print("Toiminto keskeytetään!");
            return true;
        }

        return false;
    }
}
