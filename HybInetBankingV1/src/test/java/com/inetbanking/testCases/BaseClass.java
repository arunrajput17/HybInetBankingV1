package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	// Creating Object of ReadConfig to get values
	ReadConfig readconfig = new ReadConfig();
	
	
	public String baseURL = readconfig.getApplicationURL();            //"http://demo.guru99.com/V4";
	public String username = readconfig.getUsername();		 		//"mngr284797";
	public String password = readconfig.getPassword();				//"gegygyb";
	public static WebDriver driver;
	
	public static Logger logger;

	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		
		// We need to define the class in which we are creating object
//		logger = LogManager.getLogger(BaseClass.class);	//This prints hardcoded BaseClass in logs
		logger = LogManager.getLogger(getClass().getName());

		
		
		//Below code is used for older version of Log4j version 1x
//		logger = Logger.getLogger("eBanking");
//		PropertyConfigurator.configure("Log4j.properties");	
		
		
//		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe" );	//Code changed after using readconfig
		
		// To work on multiple browsers
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
			logger.info("Chrome opened");
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
			logger.info("Firefox opened");
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
			logger.info("Internet Explorer opened");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
	}
	
	
	@AfterClass
	public void tearDown()
	{
		
		driver.quit();
	}
	
	
	// Capturing Screenshot after every Test method get failed
	
	@AfterMethod
	public void takeFailScreenshot(ITestResult result) throws IOException
	{
		if(result.getStatus()== ITestResult.FAILURE)
		{
			captureScreen(driver, result.getName());
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			System.out.println("skipped");
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			System.out.println("success");
		}
	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
	}
	
	
	
	// Generating Random String
	public String randomString(int stringLen)
	{
		String generatedString=RandomStringUtils.randomAlphabetic(stringLen);
		return generatedString;
	}
	
	// Generating Random Number String
	public String randomNumber (int numberLen)
	{
		String generatedNumber = RandomStringUtils.randomNumeric(numberLen);
		return generatedNumber;
	}

	

}
