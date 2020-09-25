package com.inetbanking.utilities;

// Listener Class used to generate Extent Reports
// Add entry in TestNG xml file

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	
//	public ExtentHtmlReporter htmlReporter;	//Use in extent report version 4.1.7
	public ExtentSparkReporter htmlReporter;	// use in extent report version 5.0.3
	public ExtentReports extent;
	public ExtentTest extlogger;
	
	
	public void onStart(ITestContext itestContext)
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());	//time stamp
		String repName = "ExtentTest-Report-"+timeStamp+".html";
//		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);	//specify location
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/extentReport-output/"+repName);	//specify location
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
//		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Tester Name", "Jamie");
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project");	//Title of report
		htmlReporter.config().setReportName("Functional Test Automation Report");	//Name of the Report
//		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);	//location of the chart	//used in old extent report
		htmlReporter.config().setTheme(Theme.DARK);	//Setting theme of report
		
	}
	
	
	public void onTestSuccess(ITestResult tr)
	{
		extlogger= extent.createTest(tr.getName()); 	//create new entry in the report
		extlogger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));	//send the passed information
	}
	
	public void onTestFailure(ITestResult tr)
	{
		extlogger=extent.createTest(tr.getName());		//create new entry in the report
		extlogger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); //send the failed information
		
		String screenshotPath= System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		
		if(f.exists())
		{
			try 
			{
				extlogger.fail("Screenshot is above:" + extlogger.addScreenCaptureFromPath(screenshotPath));
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
				
	}
	
	
	public void onTestSkipped(ITestResult tr)
	{
		extlogger= extent.createTest(tr.getName()); 	//create new entry in the report
		extlogger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));	//send the skipped information
	}
	
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
	

}
