package Lukuvinkkiohjelma;

import Lukuvinkkiohjelma.dao.StubDao;
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
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.sovelluslogiikka = new Sovelluslogiikka(new StubDao());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kirjanLisays() {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String isbn = "TestISBN";
        
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        sovelluslogiikka.lisaaVinkki(kirja);
        
        kirja = (Kirja) sovelluslogiikka.listaaKirjat().get(0);
        assertTrue(kirja.getOtsikko().equals(otsikko));
        assertTrue(kirja.getKirjoittaja().equals(kirjoittaja));
        assertTrue(kirja.getISBN().equals(isbn));
    }

    @Test
    public void kirjanPoistaminen() {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String isbn = "TestISBN";
        
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        sovelluslogiikka.lisaaVinkki(kirja);
        assertTrue(sovelluslogiikka.listaaKaikkiVinkit().size() == 1);
        
        sovelluslogiikka.poistaVinkki(0);
        assertTrue(sovelluslogiikka.listaaKaikkiVinkit().isEmpty());
    }

    @Test
    public void bloginLisays() {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String url = "TestURL";
        
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        sovelluslogiikka.lisaaVinkki(blogi);
        
        blogi = (Blogi) sovelluslogiikka.listaaBlogit().get(0);
        assertTrue(blogi.getOtsikko().equals(otsikko));
        assertTrue(blogi.getKirjoittaja().equals(kirjoittaja));
        assertTrue(blogi.getUrl().equals(url));
    }

    @Test
    public void bloginPoistaminen() {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String url = "TestURL";
        
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        sovelluslogiikka.lisaaVinkki(blogi);
        assertTrue(sovelluslogiikka.listaaKaikkiVinkit().size() == 1);
        
        sovelluslogiikka.poistaVinkki(0);
        assertTrue(sovelluslogiikka.listaaKaikkiVinkit().isEmpty());
    }

    @Test
    public void podcastinLisays() {
        String otsikko = "TestO";
        String url = "TestURL";
        
        Podcast podccast = new Podcast(otsikko, url);
        sovelluslogiikka.lisaaVinkki(podccast);
        
        podccast = (Podcast) sovelluslogiikka.listaaPodcastit().get(0);
        assertTrue(podccast.getOtsikko().equals(otsikko));
        assertTrue(podccast.getUrl().equals(url));
    }

    @Test
    public void podcastinPoistaminen() {
        String otsikko = "TestO";
        String url = "TestURL";
        
        Podcast podccast = new Podcast(otsikko, url);
        sovelluslogiikka.lisaaVinkki(podccast);
        assertTrue(sovelluslogiikka.listaaKaikkiVinkit().size() == 1);
        
        sovelluslogiikka.poistaVinkki(0);
        assertTrue(sovelluslogiikka.listaaKaikkiVinkit().isEmpty());
    }
}
