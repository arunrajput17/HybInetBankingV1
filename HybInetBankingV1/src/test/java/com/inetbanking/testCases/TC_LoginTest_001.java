package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws InterruptedException, IOException
	{
		logger.info("loginTest method started executing");
		
//		driver.get(baseURL);
		
		logger.info("URL opened");
		
		
//		logger.trace("This is trace message");
//        logger.info("This is an info message of Test method 1");	
//        logger.debug("This is a debug message");
//        logger.warn("This is a warn message");
//        logger.error("This is an error message");
//        logger.fatal("This is a fatal message");
		
		//Creating object of LoginPage
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("Entered Username");
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickSubmit();
		logger.info("Login Button Clicked");
		
		Thread.sleep(5000);
		
		
		if( driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
		}
		else
		{
//			captureScreen(driver, "loginTest");	//moved to base class
			Assert.assertTrue(false);
			logger.info("Login Test Failed");
		}
		
		
	}
	
	
}
