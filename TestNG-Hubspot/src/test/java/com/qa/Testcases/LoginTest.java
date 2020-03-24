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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;



@Listeners({AllureReports.class})
public class LoginTest  extends BasePage {
			
	public WebDriver driver;
	
	@BeforeClass
	public void setup() throws InterruptedException {
		BasePage bs =new  BasePage();
		driver = bs.init_driver();
		driver.get(URL);
		logger.info("***************URL Entered***************");
	}
	
	
	@Severity(SeverityLevel.BLOCKER)	
	@Description("Login into the Application........")
	@Epic("EP001")
	@Feature("Feature1: Login page")
	@Story("Story: Login page")
	@Step(" Login page Verifying")
	@Test(priority=1)
	public void login() throws InterruptedException {
		
		Thread.sleep(5000);
		LoginPage	login = new LoginPage(driver);
		Readconfig	config= new Readconfig();
		login.setusername().sendKeys(config.Appusername());
		login.setpassword().sendKeys(config.AppPassword());
		login.loginbutton().click();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		logger.info("*************logging in to the Application****************");
	}	
	
	
	@Severity(SeverityLevel.NORMAL)	
	@Description("Login Page Title verfication........")
	@Epic("EP002")
	@Feature("Feature2: Login page title verify")
	@Story("Story:Login page title verify")
	@Step("Login page title verify")
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
		logger.info("*************Verifying the Title****************");
	}
	
}
