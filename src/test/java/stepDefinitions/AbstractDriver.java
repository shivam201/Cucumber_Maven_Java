package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AbstractDriver {
	protected static WebDriver driver;
	
	protected static WebDriver getDriver(String BrowserName)
	{
		String Browser = "";
		if(driver==null)
		{
			// Browser Selection // 
			String CurrentDir = System.getProperty("user.dir");
			String OS = System.getProperty("os.name");
			if(BrowserName.toLowerCase().contains("chrome"))
				Browser = "CHROME";
			if(BrowserName.toLowerCase().contains("edge"))
				Browser = "EDGE";
			if(BrowserName.toLowerCase().contains("ie"))
				Browser = "IE";
			
			switch(Browser)
			{
				case "CHROME"	:	ChromeOptions options = new ChromeOptions();
									if(OS.contains("Window")) {
										CurrentDir = CurrentDir+"\\driver\\chromedriver.exe";
										//CurrentDir = "src//test/resources/com//microsoft/otto/api/integrationtests/driver/chromedriver.exe";
									}									
									else
									{
										// Check that Bash should run to set this value //
										CurrentDir = "/usr/bin/chromedriver";
									}
									//options.addArguments("--disable-dev-shm-usage");
							        //options.addArguments("--no-sandbox");
							        //options.addArguments("--remote-debugging-port=9222");
									//options.addArguments("headless");
									System.setProperty("webdriver.chrome.driver",CurrentDir);
									driver = new ChromeDriver(options);
									driver.manage().window().maximize();
									driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
									break;
									
				case "EDGE"		:	System.out.println("Driver setting is pending to add");
									break;
									
				case "IE"		:	System.out.println("Driver setting is pending to add");
									break;
									
				default 		:	System.out.println("Invalid Choice");
									break;
			
			}		
		}
		return driver;
	}
}
