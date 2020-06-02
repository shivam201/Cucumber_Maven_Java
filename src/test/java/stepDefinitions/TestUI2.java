package stepDefinitions;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import genericfunctions.GenericFunction;
import genericfunctions.Selenium_GenericFun;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import junit.framework.Assert;

public class TestUI2 extends AbstractDriver{
	WebDriver driver = getDriver("Chrome");
	GenericFunction obj = new GenericFunction();
	SoftAssertions softly = new SoftAssertions();
	Selenium_GenericFun selegf = new Selenium_GenericFun();
	
	
	@Then("^\"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" is Displayed$")
	public void and_and_and_is_Displayed(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		System.out.println("Driver title"+driver.getTitle());
		String FedexPrimaryLogoClass = obj.ReadConfigFile("FEDEX_HEADER_LOGO_CLASS");
		System.out.println("Class Name "+FedexPrimaryLogoClass);
		selegf.ElementHighlighter(driver, driver.findElement(By.className(FedexPrimaryLogoClass)));
		System.out.println(driver.findElement(By.className(FedexPrimaryLogoClass)).isDisplayed());
		softly.assertThat(driver.findElement(By.className(FedexPrimaryLogoClass)).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
	}

	@Then("^\"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in Span \"([^\"]*)\" is displayed$")
	public void and_and_and_and_in_Span_is_displayed(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		softly.assertThat(driver.findElement(By.id("")).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
//	    softly.assertThat(driver.findElement(By.id("")).isDisplayed());
		//driver.close();
		//driver.quit();
	}


	
	
	
}
