package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("Password provided");
		lp.clickSubmit();
		logger.info("Login Button Clicked");
		
		Thread.sleep(3000);
		
		if (isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();	//click on accept present in alert (close alert)
			driver.switchTo().defaultContent();	// this will focus on the main page (login page)
			logger.warn("Login failed");
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login Passed");
			Thread.sleep(3000);
			lp.clickNewCustomer();
			lp.clickLogout();
			driver.switchTo().alert().accept(); // close the logout alert
			driver.switchTo().defaultContent();
			
		}
		
		
	}
	
	
	// creating method for invalid case in which alert came or not after click login
	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	
	
	//Data provider method
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\inetbanking\\testData\\LoginData2.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int cocount = XLUtils.getCellCount(path,"Sheet1" , 	1);
		
		//creating 2D array
		String loginData[][] = new String[rownum][cocount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<cocount; j++)
			{
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);	// save data in array without header available in excel
			}
		}
		return loginData;
	}
	
	
	
	
	

}
