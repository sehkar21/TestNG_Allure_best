package com.qa.Testcases;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.Base.BasePage;
import com.qa.Pages.LoginPage;
import com.qa.Util.AllureReports;
import com.qa.config.Readconfig;



@Listeners({AllureReports.class})
public class LoginTest  extends BasePage {
			
	public WebDriver driver;
	
	@BeforeClass
	public void setup() throws InterruptedException {
		BasePage bs =new  BasePage();
		driver = bs.init_driver();
		driver.get(URL);
	}
	
	
	
	@Test(priority=1)
	public void login() throws InterruptedException {
		
		Thread.sleep(5000);
		LoginPage	login = new LoginPage(driver);
		Readconfig	config= new Readconfig();
		login.setusername().sendKeys(config.Appusername());
		login.setpassword().sendKeys(config.AppPassword());
		login.loginbutton().click();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}	
	
	@Test(priority=2)
	public void confirmTitle() throws InterruptedException {
		Thread.sleep(5000);
		String title = driver.getTitle();
		System.out.println(title);
		// SoftAssert soft = new SoftAssert();
		Assert.assertEquals(title,"Reports dashboard1");
		/*
		 * try { Assert.assertEquals(title,"Reports dashboard1"); } catch(AssertionError
		 * e) { e.printStackTrace(); }
		 */
		Thread.sleep(6000);
		
	}
	
}
