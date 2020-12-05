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
import Lukuvinkkiohjelma.domain.Vinkki;
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
            io.print("1 - Listaa vinkit");
            io.print("2 - Lisää vinkki");
            io.print("3 - Poista vinkki");
            //io.print("3 - Muokkaa vinkkiä");
            //io.print("5 - Muokkaa listauksen parametreja");
            io.print("0 - Sulje ohjelma");
            io.print("");

            String komento = io.readLine("Anna komento");
            io.print("\nTyhjä syöte eli pelkkä enter keskeyttää toiminnon.\n");

            List<Vinkki> vinkit;
            
            switch (komento) {
                case "1":
                    vinkit = sovelluslogiikka.listaaKaikkiVinkit();
                    listaaVinkit(vinkit);
                    break;
                case "2":
                    lisaaVinkki();
                    break;
                case "3":
                    {
                        vinkit = sovelluslogiikka.listaaKaikkiVinkit();
                        listaaVinkit(vinkit);
                             
                        String numeroString = io.readLine("Anna poistettavan vinkin numero:");
                        if (keskeytetaan(numeroString)) {
                            continue;
                        }       
                        int indeksi = Integer.parseInt(numeroString);
                        if (sovelluslogiikka.poistaVinkki(indeksi - 1)) {
                            io.print("\nVinkki poistettu onnistuneesti!\n");
                        } else {
                            io.print("\nJotain meni pieleen vinkin poistamisessa.\n");
                        } break;
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
    
    // Uuden vinkin lisääminen
    private void lisaaVinkki() {
        io.print("Millainen vinkki lisätään?\n\t1. Kirja\n\t2. Podcast\n\t3. Blogi\n");
        String vinkkityyppi = io.readLine("Anna komento");
        io.print("");
        if (keskeytetaan(vinkkityyppi)) {
            return;
        }   
        switch (vinkkityyppi) {
            case "1":
                {
                    String otsikko = io.readLine("Kirjan otsikko: ");
                    if (keskeytetaan(otsikko)) {
                        return;
                    }
                    String kirjoittaja = io.readLine("\nKirjan kirjoittaja: ");
                    if (keskeytetaan(kirjoittaja)) {
                        //continue;
                    }
                    String ISBN = io.readLine("\nKirjan ISBN: ");
                    if (keskeytetaan(ISBN)) {
                        return;
                    }
                    talletaVinkki(new Kirja(otsikko, kirjoittaja, ISBN));
                    
                    break;
                }
            case "2":
                {
                    String otsikko = io.readLine("Podcastin otsikko: ");
                    if (keskeytetaan(otsikko)) {
                        return;
                    }
                    String Url = io.readLine("\nPodcastin Url: ");
                    if (keskeytetaan(Url)) {
                        return;
                    }
                    talletaVinkki(new Podcast(otsikko, Url));

                    break;
                }
            case "3":
                {
                    String otsikko = io.readLine("Blogin otsikko: ");
                    if (keskeytetaan(otsikko)) {
                        //continue;
                    }
                    String kirjoittaja = io.readLine("\nBlogin kirjoittaja: ");
                    if (keskeytetaan(kirjoittaja)) {
                        return;
                    }
                    String Url = io.readLine("\nBlogin Url: ");
                    if (keskeytetaan(Url)) {
                        return;
                    }
                    talletaVinkki(new Blogi(otsikko, kirjoittaja, Url));
                    
                    break;
                }
            default:
                break;
        }
    }
    
    private void talletaVinkki(Vinkki vinkki) {
        if (sovelluslogiikka.lisaaVinkki(vinkki)) {
            io.print("\nUusi " + vinkki.getClass().getSimpleName() + "vinkki" + " lisatty onnistuneesti!\n");
        } else {
            io.print("\nJotain meni pieleen uuden kirjavinkin lisamisessa...\n");
        }
    }
    
    private void listaaVinkit(List<Vinkki> vinkit) {
        if (!vinkit.isEmpty()) {
            for (int i = 1; i < vinkit.size(); i++) {
                io.print(i + " " + vinkit.get(i));
            }
        } else {
            io.print("Ei vielä lisättyjä vinkkejä!\n");
        }
    }

    private boolean keskeytetaan(String syote) {
        if (syote.equals("")) {
            io.print("Toiminto keskeytetään!\n");
            return true;
        }

        return false;
    }
    
    private int lueLukuValilta(String prompt, int suurin) {
        while(true) {            
            int luku = io.readInt(prompt);
            if (luku <= suurin) {
               return luku; 
            } else {
                io.print("Virheellinen syöte!\n");
            }
        }
    }
   
}