package com.inetbanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass 
{
	
	@Test
	public void addNewCustomer() throws InterruptedException
	{
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User Name is provided");
		lp.setPassword(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("Providing customer details......");
		addcust.setCustName("Barbara");
		addcust.clickCustGender("male");
		addcust.setCustDob("01", "22", "1999");
		Thread.sleep(3000);
		addcust.setCustAddress("INDIA");
		addcust.setCustCity("HYD");
		addcust.setCustState("AP");
		addcust.setCustPin("1111109");
		addcust.setCustTelephoneNo("0909090901");
		
		//Email id allowed is unique
		String email = randomString(8)+"@gmail.com";
		addcust.setCustEmailID(email);
		
		addcust.setPassword("ghghhj");
		addcust.clickCustSubmit();
		Thread.sleep(3000);
		
		
		logger.info("Validation started.....");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!1");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Test case is passed");
		}
		else
		{
			logger.info("Test case is failed");
			Assert.assertTrue(false);
		}
		
		
	}
	

}
