/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma.ui;

import Lukuvinkkiohjelma.dao.VinkkiDao;
import Lukuvinkkiohjelma.domain.Blogi;
import Lukuvinkkiohjelma.domain.Kirja;
import Lukuvinkkiohjelma.domain.Podcast;
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

            switch (komento) {
                case "1":
                    io.print("Millainen vinkki lisätään?\n\t1. Kirja\n\t2. Podcast\n\t3. Blogi\n");
                    String vinkkityyppi = io.readLine("Anna komento");
                    io.print("");
                    if (keskeytetaan(vinkkityyppi)) {
                        continue;
                    }   if (vinkkityyppi.equals("1")) {
                        String otsikko = io.readLine("Kirjan otsikko: ");
                        if (keskeytetaan(otsikko)) {
                            continue;
                        }
                        
                        String kirjoittaja = io.readLine("\nKirjan kirjoittaja: ");
                        if (keskeytetaan(kirjoittaja)) {
                            continue;
                        }
                        String ISBN = io.readLine("\nKirjan ISBN: ");
                        if (keskeytetaan(ISBN)) {
                            continue;
                        }
                        
                        if (sovelluslogiikka.lisaaVinkki(new Kirja(otsikko, kirjoittaja, ISBN))) {
                            io.print("");
                            io.print("Uusi kirjavinkki lisatty onnistuneesti!");
                            io.print("");
                        } else {
                            io.print("\nJotain meni pieleen uuden kirjavinkin lisamisessa...\n");
                        }
                    } else if (vinkkityyppi.equals("2")) {
                        String otsikko = io.readLine("Podcastin otsikko: ");
                        if (keskeytetaan(otsikko)) {
                            continue;
                        }

                        String Url = io.readLine("\nPodcastin Url: ");
                        if (keskeytetaan(Url)) {
                            continue;
                        }
                        
                        if (sovelluslogiikka.lisaaVinkki(new Podcast(otsikko, Url))) {
                            io.print("");
                            io.print("Uusi podcastvinkki lisatty onnistuneesti!");
                            io.print("");
                        } else {
                            io.print("\nJotain meni pieleen uuden blogivinkin lisamisessa...\n");
                        }  break;
                    }
                    else if (vinkkityyppi.equals("3")) {
                        String otsikko = io.readLine("Blogin otsikko: ");
                        if (keskeytetaan(otsikko)) {
                            continue;
                        }
                        
                        String kirjoittaja = io.readLine("\nBlogin kirjoittaja: ");
                        if (keskeytetaan(kirjoittaja)) {
                            continue;
                        }
                        String Url = io.readLine("\nBlogin Url: ");
                        if (keskeytetaan(Url)) {
                            continue;
                        }
                        
                        if (sovelluslogiikka.lisaaVinkki(new Blogi(otsikko, kirjoittaja, Url))) {
                            io.print("");
                            io.print("Uusi blogivinkki lisatty onnistuneesti!");
                            io.print("");
                        } else {
                            io.print("\nJotain meni pieleen uuden blogivinkin lisamisessa...\n");
                        }  break;
                    }
                case "2":
                    {
                        List<Kirja> kirjalista = sovelluslogiikka.listaaKirjat();
                        // Tähän myös podcastien ja blogien haku...
                        if (!kirjalista.isEmpty()) {
                            for (Kirja kirja : kirjalista) {
                                io.print(kirja.toString());
                            }
                        } else {
                            io.print("Ei vielä lisättyjä vinkkejä!\n");
                        }       break;
                    }
                case "3":
                    {
                        List<Kirja> kirjalista = sovelluslogiikka.listaaKirjat();
                        if (!kirjalista.isEmpty()) {
                            for (int i = 0; i < kirjalista.size(); i++) {
                                io.print(i + " " + kirjalista.get(i));
                            }
                        }       String numeroString = io.readLine("Anna poistettavan vinkin numero:");
                        if (keskeytetaan(numeroString)) {
                            continue;
                        }       int numero = Integer.parseInt(numeroString);
                        if (sovelluslogiikka.poistaVinkki(kirjalista.get(numero))) {
                            io.print("\nVinkki poistettu onnistuneesti!\n");
                        } else {
                            io.print("\nJotain meni pieleen vinkin poistamisessa.\n");
                        }       break;
                    }
                case "0":
                    io.print("Kiitos käynnistä! Hei hei!");
                    kaynnissa = false;
                    break;
                default:
                    io.print("Komento tuntematon, syötä uusi komento.\n");
                    break;
            }
        }
    }

    private boolean keskeytetaan(String syote) {
        if (syote.equals("")) {
            io.print("Toiminto keskeytetään!\n");
            return true;
        }

        return false;
    }
}
