package Lukuvinkkiohjelma;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.util.ArrayList;
import static org.junit.Assert.*;
import Lukuvinkkiohjelma.dao.VinkkiDao;
import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import Lukuvinkkiohjelma.domain.Sovelluslogiikka;
import Lukuvinkkiohjelma.ui.Tekstikayttoliittyma;
import Lukuvinkkiohjelma.io.IO;
import Lukuvinkkiohjelma.io.StubIO;
import java.util.List;


public class Stepdefs {
    VinkkiJsonDao dao;
    List<String> inputLines;
    Tekstikayttoliittyma kayttoliittyma;
    StubIO io;
    
    @Before
    public void setup(){
        dao = new VinkkiJsonDao("koetiedosto.json");
        inputLines = new ArrayList<>();       
    }
    
    @Given("^command lisaa kirja is selected$")
    public void commandLisaaKirjaSelected() throws Throwable {
        inputLines.add("1");
        inputLines.add("1");
    }
    
    @Given("^command lisaa podcast is selected$")
    public void commandLisaaPodcastSelected() throws Throwable {
        inputLines.add("1");
        inputLines.add("2");
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
    
    @When("^command merkitse luetuksi is selected$")
    public void commandMerkitseLuetuksiSelected() throws Throwable {
        inputLines.add("4");
    }
    
    @When("^command muokkaa vinkki is selected$")
    public void commandMuokkaaVinkkiSelected() throws Throwable {
        inputLines.add("6");
    }
    
    @When("a new booktip with title {string}, author {string} and ISBN {string} is added")
    public void aNewBookTipIsAdded(String otsikko, String kirjoittaja, String isbn) {
        
        inputLines.add(otsikko);
        inputLines.add(kirjoittaja);
        inputLines.add(isbn);
  
    }
    
    @When("a new podcasttip with title {string} and url {string} is added")
    public void aNewPodcastTipIsAdded(String otsikko, String url) {
        
        inputLines.add(otsikko);
        inputLines.add(url);
  
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
    
    @When("confirmation {string} is entered")
    public void deleteConfirmationGiven(String confirmation) {
        inputLines.add(confirmation);
    }
    
    @When("cancel command {string} is entered")
    public void cancelGiven(String cancel) {
        inputLines.add(cancel);
    }
    
    @When("input {string} is entered")
    public void stringInput(String input) {
        inputLines.add(input);
    }
    
    @When("program is terminated")
    public void programIsTerminated() throws Throwable {
        inputLines.add("0");
        io = new StubIO(inputLines);
        kayttoliittyma = new Tekstikayttoliittyma(io, dao);
        //kayttoliittyma = new Tekstikayttoliittyma(io, stubiDao);
        kayttoliittyma.kaynnista();
    }
    
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        
        for (String print : io.getPrints()) {
            System.out.println(print);
        }
        
        // assertTrue(sisaltaaTekstin(io.getPrints(), expectedOutput));
        assertTrue(io.getPrints().contains(expectedOutput));
        
    }
    
    @Then("output will contain text {string}")
    public void outputWillContainText(String expectedOutput) {

        for (String s : io.getPrints()) {
            System.out.println(s);
        }
     
        assertTrue(includesText(io.getPrints(), expectedOutput));
        
    }
    
    private boolean includesText(List<String> list, String text) {
        
        for (String candidate : list) {
            
            if (candidate.contains(text)) { 
//                System.out.println("");
//                System.out.println("L�ydetty rivi: ");
//                System.out.println(stringi);
                
                return true;
            }
        }
        
        return false;
    }    
}