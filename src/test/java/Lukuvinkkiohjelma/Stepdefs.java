package Lukuvinkkiohjelma;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.util.ArrayList;
import static org.junit.Assert.*;
import Lukuvinkkiohjelma.dao.VinkkiDao;
import Lukuvinkkiohjelma.dao.StubiDao;
import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import Lukuvinkkiohjelma.domain.Sovelluslogiikka;
import Lukuvinkkiohjelma.ui.Tekstikayttoliittyma;
import Lukuvinkkiohjelma.io.IO;
import Lukuvinkkiohjelma.io.StubIO;
import java.util.List;

public class Stepdefs {
    StubiDao stubiDao;
    List<String> inputLines;
    Tekstikayttoliittyma kayttoliittyma;
    StubIO io;
    
    @Before
    public void setup(){
        stubiDao = new StubiDao();
        inputLines = new ArrayList<>();       
    }
    
    @Given("^command lisaa kirja is selected$")
    public void commandLisaaKirjaSelected() throws Throwable {
        inputLines.add("1");
        inputLines.add("1");
    }
    
    
    @When("a new booktip with title {string}, author {string} and ISBN {string} is added")
    public void aNewTipIsAdded(String otsikko, String kirjoittaja, String isbn) {
        
        inputLines.add(otsikko);
        inputLines.add(kirjoittaja);
        inputLines.add(isbn);
//        inputLines.add("0");
       
        io = new StubIO(inputLines);
        kayttoliittyma = new Tekstikayttoliittyma(io, stubiDao);
        kayttoliittyma.kaynnista();
           

//        io = new StubIO(inputLines);
//        kayttoliittyma = new Tekstikayttoliittyma(io, stubiDao);
//        kayttoliittyma.kaynnista();
        

        /*
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
        */
  
    }
    
    @When("program is terminated")
    public void programIsTerminated() throws Throwable {
        inputLines.add("0");
        io = new StubIO(inputLines);
        kayttoliittyma = new Tekstikayttoliittyma(io, stubiDao);
        kayttoliittyma.kaynnista();
    }
    

    
    @Then("system will respond with {string}")
    public void aNewTipCanBeFound(String expectedOutput) {
        
        for (String print : io.getPrints()) {
            System.out.println(print);
        }
        
        assertTrue(io.getPrints().contains(expectedOutput));
        
    }
    

    
    
}
