package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver) 
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(linkText="New Customer")
	@CacheLookup
	WebElement lnkNewCustomer;
	
	@FindBy(linkText="Log out")
	@CacheLookup
	WebElement lnkLogout;
	
	
	// Action Methods
	
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
	}
	
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit()
	{
		btnLogin.click();
	}
	
	public void clickNewCustomer()
	{
		lnkNewCustomer.click();
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	

}
