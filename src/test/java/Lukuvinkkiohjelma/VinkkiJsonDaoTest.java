package Lukuvinkkiohjelma;

import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void setUp() {
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
}
