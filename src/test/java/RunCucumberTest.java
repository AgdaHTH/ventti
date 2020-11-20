/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

/**
 *
 * @author Hilla
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/ohtu",
        snippets = SnippetType.CAMELCASE
)
public class RunCucumberTest {
}
