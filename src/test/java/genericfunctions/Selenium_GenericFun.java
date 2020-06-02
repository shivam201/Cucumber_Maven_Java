package genericfunctions;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.Scenario;

public class Selenium_GenericFun {
	/**
	 * This will capture screenshot after every Scenario
	 * @param scenario
	 * @param driver
	 */
	public void tearDown(Scenario scenario,WebDriver driver)
	{
		try 
		{
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			File screenshot_with_scenario_name = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String FileName = scenario.getName().replaceAll(" ","")+".png";
			String OS = System.getProperty("os.name").toLowerCase();
			if(OS.contains("window"))
				FileName = System.getProperty("user.dir")+"\\target\\Screenshot\\"+FileName;
			
			else
				FileName = System.getProperty("user.dir")+"/target/Screenshot/"+FileName;
		
			System.out.println("Filename "+FileName);	
			
			File file = new File(FileName);
			file.getParentFile().mkdirs(); // Will create parent directories if not exists
			file.createNewFile();
			FileOutputStream s = new FileOutputStream(file,false);
			FileUtils.copyFile(screenshot_with_scenario_name,new File(FileName));
			System.out.println(scenario.getName());
			scenario.embed(screenshot,"image/png");
			s.close();
		}
		catch(Exception e)
		{
			System.out.println("Not Able to take screenshot May be a case of API where driver can give exception as "+e);
		}		
	}	
	
	/**
	 * Take Screenshot Default
	 * @param ScreenShotName
	 * @param driver
	 */
	public void TakeScreenShot(String ScreenShotName,WebDriver driver)
	{
		try 
		{
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			File screenshot_with_scenario_name = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String FileName = ScreenShotName.replaceAll(" ","")+".png";
			String OS = System.getProperty("os.name").toLowerCase();
			if(OS.contains("window"))
				FileName = System.getProperty("user.dir")+"\\target\\Screenshot\\"+FileName;
			
			else
				FileName = System.getProperty("user.dir")+"/target/Screenshot/"+FileName;
		
			System.out.println("Filename "+FileName);	
			
			File file = new File(FileName);
			file.getParentFile().mkdirs(); // Will create parent directories if not exists
			file.createNewFile();
			FileOutputStream s = new FileOutputStream(file,false);
			FileUtils.copyFile(screenshot_with_scenario_name,new File(FileName));
			s.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception = "+e);
		}		
	}
	
	/**
	 * Always use this Function before Taking Screenshot
	 * @param driver
	 * @param element
	 */
	public void ElementHighlighter(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
	}
}
