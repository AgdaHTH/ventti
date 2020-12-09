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
            io.print("1 - Lisää vinkki");
            io.print("2 - Listaa vinkit");
            io.print("3 - Poista vinkki");
            io.print("4 - Merkitse vinkki luetuksi");
            io.print("5 - Muokkaa listauksen parametreja");
            io.print("6 - Muokkaa vinkkiä");
            io.print("0 - Sulje ohjelma");
            io.print("");

            io.print("Tyhjä syöte eli pelkkä enter keskeyttää toiminnon.\n");
            String komento = io.readLine("Anna komento");

            List<Vinkki> vinkit;
            
            switch (komento) {
                case "2":
                    vinkit = sovelluslogiikka.listaaParametrienMukaan();                    
                    listaaVinkit(vinkit);
                    break;
                case "1":
                    lisaaVinkki();
                    break;
                case "3":
                    poistaVinkki();
                    break;
                case "4":                
                    merkitseVinkkiLuetuksi();  
                    break;
                case "5":                
                    valitseListauksenParametrit();  
                    break;
                case "6":
                    muokkaaVinkki();
                    break;
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
                        return;
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
                        return;
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
            String tyyppi = vinkki.getClass().getSimpleName().toLowerCase();
            io.print("");
            io.print("Uusi " + tyyppi + "vinkki" + " lisatty onnistuneesti!");
            io.print("");
            
            io.print(vinkki.toString());
        } else {
            io.print("\nJotain meni pieleen uuden kirjavinkin lisamisessa...\n");
        }
    }
    
    private void listaaVinkit(List<Vinkki> vinkit) {
        io.print("");
        if (!vinkit.isEmpty()) {
            for (int i = 0; i < vinkit.size(); i++) {
                io.print(i + " " + vinkit.get(i));
            }
        } else {
            io.print("Ei vielä lisättyjä vinkkejä!\n");
        }
    }

    private void merkitseVinkkiLuetuksi() {
        List<Vinkki> vinkit = sovelluslogiikka.listaaKaikkiVinkit();
        listaaVinkit(vinkit);
        
        if (vinkit.isEmpty()) {
            return;
        }
        
        String numeroString = io.readLine("Anna luetun tai kuunnellun vinkin numero:");
        
        if (keskeytetaan(numeroString)) {
            return;
        }       
        int indeksi = Integer.parseInt(numeroString);
                        
        if (indeksi >= vinkit.size() || indeksi < 0) {
            io.print("Ei vinkkiä syötetyllä numerolla!\n");
        } else {
            //hae ensin vinkin tiedot
            //ja luo niiden avulla uusi vinkki + toggleLuettu()
            
            //Vinkki luettu = sovelluslogiikka.haeVinkki(indeksi);
            Vinkki luettu = vinkit.get(indeksi);
            //io.print(luettu.toString());
            luettu.toggleLuettu();
            //io.print(luettu.toString());
            //sitten poista vanha
            sovelluslogiikka.poistaVinkki(indeksi);
            //ja lopuksi uusi talletetaan
            sovelluslogiikka.lisaaVinkki(luettu);
            io.print("");
            io.print(luettu.toString());
            io.print("");
            io.print("Vinkki merkittiin luetuksi/kuunnelluksi!");
            io.print("");
        }
    }
    
    private void poistaVinkki() {
        List<Vinkki> vinkit = sovelluslogiikka.listaaKaikkiVinkit();
        listaaVinkit(vinkit);
        
        if (vinkit.isEmpty()) {
            return;
        }
        
        String numeroString = io.readLine("Anna poistettavan vinkin numero:");
        
        if (keskeytetaan(numeroString)) {
            return;
        }       
        int indeksi = Integer.parseInt(numeroString);
                        
        if (indeksi >= vinkit.size() || indeksi < 0) {
            io.print("Ei vinkkiä syötetyllä numerolla!\n");
        } else {
            String vahvistus = io.readLine("Poistetaanko varmasti? y/n:");

            switch (vahvistus) {
                case "y":
                    if (sovelluslogiikka.poistaVinkki(indeksi)) {
                        io.print("\nVinkki poistettu onnistuneesti!\n");
                    } else {
                        io.print("\nJotain meni pieleen vinkin poistamisessa.\n");
                    } break;                                
                default:
                    io.print("Ei poistettu mitään!\n");
                    break;
            } 
            
        }                        
    }
    
    private void muokkaaVinkki() {
        List<Vinkki> vinkit = sovelluslogiikka.listaaKaikkiVinkit();
        if (vinkit.isEmpty()) {
            return;
        }
        
        listaaVinkit(vinkit);
        
        String vinkki = io.readLine("Anna muokattavan vinkin numero, tyhj� sy�te keskeytt�� muokkaamisen: ");
        
        if (keskeytetaan(vinkki)) {
            return;
        }
        
        int indeksi = Integer.parseInt(vinkki);
        if (indeksi >= vinkit.size() || indeksi < 0) {
            io.print("\nEi vinkkiä syötetyllä numerolla!\n");
        } else {
            
            Vinkki muokattava = vinkit.get(indeksi);
            boolean luettava = false;
            boolean kirja = false;
        
            if (muokattava instanceof Kirja) {
                
                luettava = true;
                kirja = true;
                
                io.print("0 Otsikko: " + ((Kirja) muokattava).getOtsikko());
                io.print("1 Kirjoittaja: " + ((Kirja) muokattava).getKirjoittaja());
                io.print("2 ISBN: " + ((Kirja) muokattava).getISBN());
            } else if (muokattava instanceof Podcast) {
                
                io.print("0 Otsikko: " + ((Podcast) muokattava).getOtsikko());
                io.print("1 URL: " + ((Podcast) muokattava).getUrl());
            } else if (muokattava instanceof Blogi) {
                
                luettava = true;
                io.print("0 Otsikko: " + ((Blogi) muokattava).getOtsikko());
                io.print("1 Kirjoittaja: " + ((Blogi) muokattava).getKirjoittaja());
                io.print("2 URL: " + ((Blogi) muokattava).getUrl());
            }
        
            io.print("");
            String kentta = io.readLine("Mitä kenttää muokataan: ");
            
            if (keskeytetaan(kentta)) {
                return;
            }
            
            io.print("");
            
            int indeksikentta = Integer.parseInt(kentta);
            
            if ((luettava && kirja) && (indeksikentta >= 0 && indeksikentta <= 3)) {
                
                switch (kentta) {
                    case "0":
                        io.print("Nykyinen otsikko: " + ((Kirja) muokattava).getOtsikko());
                        String uusiOtsikko = io.readLine("Uusi otsikko: ");
                        ((Kirja)muokattava).setOtsikko(uusiOtsikko);
                        break;
                        
                    case "1": 
                        io.print("Nykyinen kirjoittaja: " + ((Kirja) muokattava).getKirjoittaja());
                        String uusiKirjoittaja = io.readLine("Uusi kirjoittaja: ");
                        ((Kirja)muokattava).setKirjoittaja(uusiKirjoittaja);
                        break;
                        
                    case "2":
                        io.print("Nykyinen ISBN: " + ((Kirja) muokattava).getISBN());
                        String uusiISBN = io.readLine("Uusi ISBN: ");
                        ((Kirja)muokattava).setISBN(uusiISBN);
                        break;
                        
                    default:
                        io.print("Ei muokattu mitään!\n");
                        return;
                }
                                
            } else if ((luettava && !kirja) && (indeksikentta >= 0 && indeksikentta <= 3)) {
                
                switch (kentta) {
                    case "0":
                        io.print("Nykyinen otsikko: " + ((Blogi) muokattava).getOtsikko());
                        String uusiOtsikko = io.readLine("Uusi otsikko: ");
                        ((Blogi)muokattava).setOtsikko(uusiOtsikko);
                        break;
                        
                    case "1": 
                        io.print("Nykyinen kirjoittaja: " + ((Blogi) muokattava).getKirjoittaja());
                        String uusiKirjoittaja = io.readLine("Uusi kirjoittaja: ");
                        ((Blogi)muokattava).setKirjoittaja(uusiKirjoittaja);
                        break;
                        
                    case "2":
                        io.print("Nykyinen URL: " + ((Blogi) muokattava).getUrl());
                        String uusiURL = io.readLine("Uusi URL: ");
                        ((Blogi)muokattava).setISBN(uusiURL);
                        break;
                        
                    default:
                        io.print("Ei muokattu mitään!\n");
                        return;
                }
                
            } else if (!luettava && (indeksikentta >= 0 && indeksikentta <= 2)) {
                switch (kentta) {
                    case "0":
                        io.print("Nykyinen otsikko: " + ((Podcast) muokattava).getOtsikko());
                        String uusiOtsikko = io.readLine("Uusi otsikko: ");
                        ((Podcast)muokattava).setOtsikko(uusiOtsikko);
                        break;
                        
                    case "1":
                        io.print("Nykyinen URL: " + ((Podcast) muokattava).getUrl());
                        String uusiURL = io.readLine("Uusi URL: ");
                        ((Podcast)muokattava).setUrl(uusiURL);
                        break;
                        
                    default:
                        io.print("Ei muokattu mitään!\n");
                        return;
                }
            } else {
                io.print("Ei kenttää syötetyllä luvulla");
                return;
            }     
            
            if (sovelluslogiikka.muokkaaVinkki(indeksi, muokattava)) {
                io.print("\nVinkkiä muokattu onnistuneesti!\n");
            } else {
                io.print("\nJotain meni pieleen muokkauksessa\n");
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

    private void valitseListauksenParametrit() {
        while(true) {
           io.print("");
           io.print("Valitse listaukseen sisältyvät asiat");
           
            String luettu = sovelluslogiikka.getListauksenParametri("luettu") ? "x" : " ";
            String lukematta = sovelluslogiikka.getListauksenParametri("lukematta") ? "x" : " ";
            String kirja = sovelluslogiikka.getListauksenParametri("kirja") ? "x" : " ";
            String podcast = sovelluslogiikka.getListauksenParametri("podcast") ? "x" : " ";
            String blogi = sovelluslogiikka.getListauksenParametri("blogi") ? "x" : " ";
        
            String parametrit = ""
                    + "1:[" + luettu + "] Luetut, "
                    + "2:[" + lukematta + "] Lukematta, "
                    + "3:[" + kirja + "] Kirjat, "
                    + "4:[" + podcast + "] Podcastit, "
                    + "5:[" + blogi + "] Blogit, "
                    + "6: valitse kaikki";
            
            io.print(parametrit); 
            io.print("");
            
            String valinta = io.readLine("Anna parametrin numero. Tyhjä syöte keskeyttää toiminnon.");
                if (keskeytetaan(valinta)) {
                return;
            }
                
            switch (valinta) {
                case "1":
                    sovelluslogiikka.muutaListauksenParametria("luettu", !sovelluslogiikka.getListauksenParametri("luettu"));
                    break;
                case "2":
                    sovelluslogiikka.muutaListauksenParametria("lukematta", !sovelluslogiikka.getListauksenParametri("lukematta"));
                    break;
                case "3":
                    sovelluslogiikka.muutaListauksenParametria("kirja", !sovelluslogiikka.getListauksenParametri("kirja"));
                    break;
                case "4":
                    sovelluslogiikka.muutaListauksenParametria("podcast", !sovelluslogiikka.getListauksenParametri("podcast"));
                    break;
                case "5":
                    sovelluslogiikka.muutaListauksenParametria("blogi", !sovelluslogiikka.getListauksenParametri("blogi"));
                    break;
                case "6":
                    sovelluslogiikka.kaikkiParametritPaalle();
                    break;
                default:
                    break;
            }
        }
        
    }
   
}