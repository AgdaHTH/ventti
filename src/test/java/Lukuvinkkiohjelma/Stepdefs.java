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
import java.util.List;

public class Stepdefs {
    StubiDao stubiDao;
    List<String> inputLines;
    
    @Before
    public void setup(){
        stubiDao = new StubiDao();
        inputLines = new ArrayList<>();      
    }
    
    @Given("^command lisaa is selected$")
    public void commandLisaaSelected() throws Throwable {
        
    }
    
    @When("a new tip with title {string} and type {string} is added")
    public void aNewTipIsAdded() {
  
    }
    
    @Then("a tip with title {string} and type {string} is found in the application")
    public void aNewTipCanBeFound() {
    
    }
    
    
}
