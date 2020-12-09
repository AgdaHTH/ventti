/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma;

import Lukuvinkkiohjelma.domain.Blogi;
import Lukuvinkkiohjelma.domain.Kirja;
import Lukuvinkkiohjelma.domain.Podcast;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author juhakaup
 */
public class VinkkiTest {
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
    public void KirjanEqualsToimiiOikein() {
        String otsikko = "Testiotsikko";
        String kirjoittaja = "Testi Kirjoittaja";
        String isbn = "1234567890";
        
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        Kirja kirjaKopio = new Kirja(otsikko, kirjoittaja, isbn);
        Kirja toinenKirja = new Kirja(otsikko, "Toinen Kirjoittaja", "1234");
        Blogi blogi = new Blogi("Otsikko", "Kirjoittaja", "url");
        
        assertTrue(kirja.equals(kirja));
        assertTrue(kirja.equals(kirjaKopio));
        assertFalse(kirja.equals(toinenKirja));
        assertFalse(kirja.equals(blogi));
    }
    
    @Test
    public void BloginEqualsToimiiOikein() {
        String otsikko = "Testiotsikko";
        String kirjoittaja = "Testi Kirjoittaja";
        String url = "www.com";
        
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        Blogi kopio = new Blogi(otsikko, kirjoittaja, url);
        Blogi toinenBlogi = new Blogi("blogiotsikko", "Toinen Kirjoittaja", url);
        Kirja kirja = new Kirja(otsikko, "Toinen Kirjoittaja", "1234");
        
        assertTrue(blogi.equals(blogi));
        assertTrue(blogi.equals(kopio));
        assertFalse(kirja.equals(toinenBlogi));
        assertFalse(blogi.equals(kirja));
    }
    
    @Test
    public void PodcastinEqualsToimiiOikein() {
        String otsikko = "Testiotsikko";
        String url = "www.com";
        
        Podcast podcast = new Podcast(otsikko, url);
        Podcast kopio = new Podcast(otsikko, url);
        Podcast toinenPodcast = new Podcast("ToinenOtsikko", "www.org");
        Kirja kirja = new Kirja(otsikko, "Toinen Kirjoittaja", "1234");
        
        assertTrue(podcast.equals(podcast));
        assertTrue(podcast.equals(kopio));
        assertFalse(kirja.equals(toinenPodcast));
        assertFalse(podcast.equals(kirja));
    }
}
