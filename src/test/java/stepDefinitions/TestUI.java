package stepDefinitions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import genericfunctions.GenericFunction;
import genericfunctions.Selenium_GenericFun;

public class TestUI extends AbstractDriver{
	static WebDriver driver ;
	GenericFunction obj = new GenericFunction();
	SoftAssertions softly = new SoftAssertions();
	
	
	@Given("^User Open \"([^\"]*)\" Browser$")
	public void user_Open_Browser(String BrowserName) throws Throwable {
		driver = getDriver(BrowserName);
		System.out.println("");
		//driver.manage().window().maximize();
	}

	@When("^User Navigate to \"([^\"]*)\"$")
	public void user_Navigate_to(String LUMINAPORTALLINK) throws Throwable {
		String PORTALLINK = obj.ReadConfigFile("COMPANYPORTALURL");
		System.out.println(PORTALLINK);
		driver.navigate().to(PORTALLINK);
	}
	
	@Then("^\"([^\"]*)\" Opens$")
	public void opens(String arg1) throws Throwable {
		String COMPANYPORTALTITLE = obj.ReadConfigFile("CompanyPortalPAGETITLE");
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		softly.assertThat(driver.getTitle().contains(COMPANYPORTALTITLE)).isTrue();
	}
	
	
	@After 
	public void close(Scenario scenario) throws Exception 
	{
		System.out.println("Executing Scenario");
		Selenium_GenericFun gfobj = new Selenium_GenericFun();
		
		try {
			gfobj.tearDown(scenario, driver);
		}catch(Exception e)
		{
			System.out.println("Issue in taking screenshot May be case is of API");
		}
	}
}