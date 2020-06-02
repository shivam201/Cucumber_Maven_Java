package runners;
/**
 * -- Generic TAG -- 
 * Sprint wise Tag = @Sprint1
 * Regression Tag = @Regression
 * 
 *  Specific Tag will need to mentioned specifically
 *  
 */

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = {"src/test/resources/PORTAL","src/test/resources/API"},
glue= {"stepDefinitions"},
tags = {"@GETAPITEST , @GETAPIFAILTEST,@POSTAPITEST"},
//tags = {"@Sprint1A"},
plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
)


public class TestRunner {
	@After
	public void after()
	{
		System.out.println("After each scenario ");
	}
	
	
	@AfterClass
    public static void writeExtentReport() {
     //Reporter.loadXMLConfig(new File("config/report.xml"));    
    }
}