package Lukuvinkkiohjelma;

import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import Lukuvinkkiohjelma.domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ventti
 */
public class SovelluslogiikkaTest {

    private Sovelluslogiikka sovelluslogiikka;

    public SovelluslogiikkaTest() {
        this.sovelluslogiikka = new Sovelluslogiikka(new VinkkiJsonDao("testi.json"));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kirjanLisaysOnnistuu() {
        String otsikko = "TestO";
        Kirja kirja = new Kirja(otsikko, "TestK", "TestISBN");
        sovelluslogiikka.lisaaVinkki(kirja);
        kirja = (Kirja) sovelluslogiikka.listaaKirjat().get(0);
        assertTrue(kirja.getOtsikko().equals(otsikko));
    }

//    @Test
//    public void kirjanPoistaminenOnnistuu() {
//        Kirja kirja = new Kirja("TestO", "TestK", "TestISBN");
//        sovelluslogiikka.lisaaVinkki(kirja);
//        assertTrue(sovelluslogiikka.listaaKirjat().contains(kirja));
//        sovelluslogiikka.poistaVinkki(0);
//        assertFalse(sovelluslogiikka.listaaKirjat().contains(kirja));
//    }
//
//    @Test
//    public void bloginLisaysOnnistuu() {
//        Blogi blogi = new Blogi("TestO", "TestK", "TestURL");
//        sovelluslogiikka.lisaaVinkki(blogi);
//        assertTrue(sovelluslogiikka.listaaBlogit().contains(blogi));
//    }
//
//    @Test
//    public void bloginPoistaminenOnnistuu() {
//        Blogi blogi = new Blogi("TestO", "TestK", "TestURL");
//        sovelluslogiikka.lisaaVinkki(blogi);
//        assertTrue(sovelluslogiikka.listaaBlogit().contains(blogi));
//        sovelluslogiikka.poistaVinkki(0);
//        assertFalse(sovelluslogiikka.listaaBlogit().contains(blogi));
//    }
//
//    @Test
//    public void podcastinLisaysOnnistuu() {
//        Podcast podcast = new Podcast("TestO", "TestURL");
//        sovelluslogiikka.lisaaVinkki(podcast);
//        assertTrue(sovelluslogiikka.listaaPodcastit().contains(podcast));
//    }
//
//    @Test
//    public void podcastinPoistaminenOnnistuu() {
//        Podcast podcast = new Podcast("TestO", "TestURL");
//        sovelluslogiikka.lisaaVinkki(podcast);
//        assertTrue(sovelluslogiikka.listaaPodcastit().contains(podcast));
//        sovelluslogiikka.poistaVinkki(0);
//        assertFalse(sovelluslogiikka.listaaBlogit().contains(podcast));
//    }
}
