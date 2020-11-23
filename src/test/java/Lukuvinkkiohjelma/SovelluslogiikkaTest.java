/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma;

import Lukuvinkkiohjelma.domain.Sovelluslogiikka;
import Lukuvinkkiohjelma.domain.Vinkki;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hilla
 */
public class SovelluslogiikkaTest {

    Sovelluslogiikka sovelluslogiikka;

    public SovelluslogiikkaTest() {
        sovelluslogiikka = new Sovelluslogiikka();
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void vinkinLisaysOnnistuu() {
        sovelluslogiikka.lisaaVinkki(new Vinkki("otsikko", "tyyppi"));
    }
}
