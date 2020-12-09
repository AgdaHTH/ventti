package Lukuvinkkiohjelma;

import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import Lukuvinkkiohjelma.domain.Blogi;
import Lukuvinkkiohjelma.domain.Kirja;
import Lukuvinkkiohjelma.domain.Podcast;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Eemeli
 */
public class VinkkiJsonDaoTest {

    private VinkkiJsonDao dao;

    public VinkkiJsonDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        this.dao = new VinkkiJsonDao("testi.json");
    }

    @After
    public void tearDown() {
        new File("testi.json").delete();
    }

    @Test
    public void daoLuoTiedoston() {
        assertTrue(new File("testi.json").exists());
    }

    @Test
    public void kirjanLisays() {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String isbn = "TestISBN";
        
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        dao.lisaaVinkki(kirja);
        
        kirja = (Kirja) dao.getKirjat().get(0);
        assertTrue(kirja.getOtsikko().equals(otsikko));
        assertTrue(kirja.getKirjoittaja().equals(kirjoittaja));
        assertTrue(kirja.getISBN().equals(isbn));
    }

    @Test
    public void kirjanPoistaminen() throws IOException {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String isbn = "TestISBN";
        
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        dao.lisaaVinkki(kirja);
        assertTrue(dao.haeKaikki().size() == 1);
        
        dao.poistaVinkki(0);
        assertTrue(dao.haeKaikki().isEmpty());
    }

    @Test
    public void bloginLisays() {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String url = "TestURL";
        
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        dao.lisaaVinkki(blogi);
        
        blogi = (Blogi) dao.getBlogit().get(0);
        assertTrue(blogi.getOtsikko().equals(otsikko));
        assertTrue(blogi.getKirjoittaja().equals(kirjoittaja));
        assertTrue(blogi.getUrl().equals(url));
    }

    @Test
    public void bloginPoistaminen() throws IOException {
        String otsikko = "TestO";
        String kirjoittaja = "TestK";
        String url = "TestURL";
        
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        dao.lisaaVinkki(blogi);
        assertTrue(dao.haeKaikki().size() == 1);
        
        dao.poistaVinkki(0);
        assertTrue(dao.haeKaikki().isEmpty());
    }

    @Test
    public void podcastinLisays() {
        String otsikko = "TestO";
        String url = "TestURL";
        
        Podcast podcast = new Podcast(otsikko, url);
        dao.lisaaVinkki(podcast);
        
        podcast = (Podcast) dao.getPodcastit().get(0);
        assertTrue(podcast.getOtsikko().equals(otsikko));
        assertTrue(podcast.getUrl().equals(url));
    }

    @Test
    public void podcastinPoistaminen() throws IOException {
        String otsikko = "TestO";
        String url = "TestURL";
        
        Podcast podcast = new Podcast(otsikko, url);
        dao.lisaaVinkki(podcast);
        assertTrue(dao.haeKaikki().size() == 1);
        
        dao.poistaVinkki(0);
        assertTrue(dao.haeKaikki().isEmpty());
    }
}
