package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	WebDriver ldriver;
	
	public AddCustomerPage (WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(how=How.LINK_TEXT, using="New Customer")
	@CacheLookup
	WebElement lnkNewCustomer;
	
	@FindBy(how=How.NAME, using="name")
	@CacheLookup
	WebElement txtCustomerName;
	
	@FindBy(how=How.NAME, using="rad1")
	@CacheLookup
	WebElement rdGender;
	
	@FindBy(how=How.ID, using="dob")
	@CacheLookup
	WebElement txtdob;
	
	@FindBy(how=How.NAME, using="addr")
	@CacheLookup
	WebElement txtaddress;
	
	@FindBy(how=How.NAME, using="city")
	@CacheLookup
	WebElement txtcity;
	
	@FindBy(how=How.NAME, using="state")
	@CacheLookup
	WebElement txtstate;
	
	@FindBy(how=How.NAME, using="pinno")
	@CacheLookup
	WebElement txtpinno;
	
	@FindBy(how=How.NAME, using="telephoneno")
	@CacheLookup
	WebElement txttelephoneno;
	
	@FindBy(how=How.NAME, using="emailid")
	@CacheLookup
	WebElement txtemailid;
	
	@FindBy(how=How.NAME, using="password")
	@CacheLookup
	WebElement txtpassword;
	
	@FindBy(how=How.NAME, using="sub")
	@CacheLookup
	WebElement btnSubmit;
	
	@FindBy(how=How.NAME, using="res")
	@CacheLookup
	WebElement btnres;
	

	// Action Methods
	
	public void clickAddNewCustomer()
	{
		lnkNewCustomer.click();
	}
	
	public void setCustName(String cname)
	{
		txtCustomerName.sendKeys(cname);
	}
	
	public void clickCustGender(String cgender)
	{
		rdGender.click();
	}
	
	public void setCustDob(String mm, String dd, String yy)
	{
		txtdob.sendKeys(mm);
		txtdob.sendKeys(dd);
		txtdob.sendKeys(yy);
	}
	
	public void setCustAddress(String caddress)
	{
		txtaddress.sendKeys(caddress);
	}
	
	public void setCustCity(String ccity)
	{
		txtcity.sendKeys(ccity);
	}
	
	public void setCustState(String cstate)
	{
		txtstate.sendKeys(cstate);
	}
	
	public void setCustPin(String cpin)
	{
		txtpinno.sendKeys(String.valueOf(cpin));	//If cpin is of int type then use this format
	}
	
	public void setCustTelephoneNo(String ctelephoneno)
	{
		txttelephoneno.sendKeys(ctelephoneno);
	}
	
	public void setCustEmailID(String cemailid)
	{
		txtemailid.sendKeys(cemailid);
	}
	
	public void setPassword(String cpassword)
	{
		txtpassword.sendKeys(cpassword);
	}
	
	public void clickCustSubmit()
	{
		btnSubmit.click();
	}
	
	public void clickCustReset()
	{
		btnres.click();
	}
	
	
	

}
