package genericfunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Scenario;

public class GenericFunction {
	/**
	 * Read Specific Key from Mentioned ConfigFile
	 * @author SHIVAM 
	 * @param ConfigFile
	 * @param Key
	 * @return Value
	 * @throws IOException
	 */
	public String ReadConfigFile(String Key) throws IOException
	{
		String Value = "";
		String ENV_SETUP_CONFIG_FILE = "envsetup.properties";
		
		String CONFIG_FILE = "";
		String APICONFIG_FILE = "";
		String OBJECTREPO_FILE = "";
		
		
		Properties prop = new Properties();
		
		String OS = System.getProperty("os.name");
		if(OS.toLowerCase().contains("window"))
			ENV_SETUP_CONFIG_FILE = System.getProperty("user.dir")+"\\config\\"+ENV_SETUP_CONFIG_FILE;
		else
			ENV_SETUP_CONFIG_FILE = System.getProperty("user.dir")+"/config/"+ENV_SETUP_CONFIG_FILE;
		
		// Property File Name updated as per Environment File //
		try 
		{
			prop.load(new FileInputStream(ENV_SETUP_CONFIG_FILE));	
			CONFIG_FILE = prop.getProperty("configfilename");
			APICONFIG_FILE = prop.getProperty("apiconfigfilename");
			OBJECTREPO_FILE = prop.getProperty("objectrepofileName");
		}
		catch(Exception e)
		{
			System.out.println("Exception in reading config File "+e);
		}
		
		
		// Read all the Property File //
		Properties prop1 = new Properties();
		Properties prop2 = new Properties();
		Properties prop3 = new Properties();
		if(OS.toLowerCase().contains("window"))
		{
			CONFIG_FILE = System.getProperty("user.dir")+"\\config\\"+CONFIG_FILE;
			APICONFIG_FILE = System.getProperty("user.dir")+"\\config\\"+APICONFIG_FILE;
			OBJECTREPO_FILE = System.getProperty("user.dir")+"\\config\\"+OBJECTREPO_FILE;
		}
		else
		{
			CONFIG_FILE = System.getProperty("user.dir")+"/config/"+CONFIG_FILE;
			APICONFIG_FILE = System.getProperty("user.dir")+"/config/"+APICONFIG_FILE;
			OBJECTREPO_FILE = System.getProperty("user.dir")+"/config/"+OBJECTREPO_FILE;
		}
		
		try 
		{
			prop1.load(new FileInputStream(CONFIG_FILE));
			prop2.load(new FileInputStream(APICONFIG_FILE));
			prop3.load(new FileInputStream(OBJECTREPO_FILE));
			Value = prop1.getProperty(Key);
			if(Value==null || Value.length()==0)
				Value = prop2.getProperty(Key);
			
			if(Value==null || Value.length()==0)
				Value = prop3.getProperty(Key);
		
			if(Value==null)
				System.out.println("Getting null value for this Key Please Check");
		}
		catch(Exception e)
		{
			System.out.println("Error while reading Key from Config/APICONFIG/OBJECTREPO File"+e);
		}
		
		
		
		return Value;
	}
	
	
	
	
	
}
