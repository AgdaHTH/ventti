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
    
    @Given("^command lisaa blogi is selected$")
    public void commandLisaaBlogiSelected() throws Throwable {
        inputLines.add("1");
        inputLines.add("3");
    }
    
    @When("^command listaa vinkit is selected$")
    public void commandListaaIsSelected() throws Throwable {
        inputLines.add("2");

    }
    
    
    @When("a new booktip with title {string}, author {string} and ISBN {string} is added")
    public void aNewBookTipIsAdded(String otsikko, String kirjoittaja, String isbn) {
        
        inputLines.add(otsikko);
        inputLines.add(kirjoittaja);
        inputLines.add(isbn);
  
    }
    
    @When("a new blogtip with title {string}, author {string} and url {string} is added")
    public void aNewBlogTipIsAdded(String otsikko, String kirjoittaja, String url) {
        
        inputLines.add(otsikko);
        inputLines.add(kirjoittaja);
        inputLines.add(url);
  
    }
    
    @When("^command poista vinkki is selected$")
    public void commandPoistaVinkkiSelected() {
        inputLines.add("3");
    }
    
    @When("tip number {string} is selected")
    public void firstTipSelected(String number) {
        System.out.println("selected " + number);
        inputLines.add(number);
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
        
        // assertTrue(sisaltaaTekstin(io.getPrints(), expectedOutput));
        assertTrue(io.getPrints().contains(expectedOutput));
        
    }
    
    @Then("output will contain text {string}")
    public void outputWillContainText(String expectedOutput) {        
     
        assertTrue(includesText(io.getPrints(), expectedOutput));
        
    }
//    
    private boolean includesText(List<String> list, String text) {
        
        for (String candidate : list) {
            
            if (candidate.contains(text)) { 
//                System.out.println("");
//                System.out.println("Löydetty rivi: ");
//                System.out.println(stringi);
                
                return true;

            }
        }
        
        return false;
    }
    
    
}
