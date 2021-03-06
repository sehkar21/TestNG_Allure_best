package com.qa.Testcases;



import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;


import com.qa.Pages.ContactsPage;

import com.qa.Util.ExcelUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;






public class ContactsTest extends LoginTest{
	
	
	@Severity(SeverityLevel.BLOCKER)	
	@Description("create contact on Home Page........")
	@Epic("EP003")
	@Feature("Feature3: contacts")
	@Story("Story:contatcs creation")
	@Step("creating contacts")
	@Test(priority=3,enabled=true)
	public void createcontact() throws InterruptedException  {
		
		
		ContactsPage contacts = new ContactsPage(driver);
	
		contacts.ClickcontactLink().click();
		contacts.ClickcontactBtn().click();

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		contacts.Createcontact().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String email=randomestring()+"@gmail.com";
		contacts.contactEmail().sendKeys(email);
		
		String firstname = randomestring();
		contacts.contactFname().sendKeys(firstname);
		String lastname = randomestring();
		contacts.contactLname().sendKeys(lastname);
		
		contacts.contactJobtitle().sendKeys("Manager");
		
		String number = randomeNum();
		contacts.contactPhonenumber().sendKeys(number);
		
		
		contacts.contactLeadstatusbtn().click();
		contacts.contactLeadstatusbtn().click();
		
		contacts.createcontactbtn().click();
		
		logger.info("************** Creating new Contacts ***************");
	}
	
	@Severity(SeverityLevel.MINOR)	
	@Description("verify  Home Page........")
	@Epic("EP006")
	@Feature("Feature6: Homepage title")
	@Story("Story: Homepage title")
	@Step(" Homepage title Verifying")
	@Test(priority =6)
	public void Homepagetitle() {
		Assert.assertTrue(false);
	}
	
	@Severity(SeverityLevel.CRITICAL)	
	@Description("WebTable Reading.......")
	@Epic("EP004")
	@Feature("Feature4: Reading WebTable")
	@Story("Story:  Reading WebTable")
	@Step("Verifying  WebTable")
	@Test(priority=4,dependsOnMethods = {"createcontact"}, enabled =true)
	public void Webtable() throws InterruptedException {
		
		Thread.sleep(5000);
		ContactsPage contacts = new ContactsPage(driver);
		contacts.ClickcontactLink().click();
		contacts.ClickcontactBtn().click();

		Thread.sleep(7000);
		
		List<WebElement> trow = contacts.TableRow();
		for(int i=0;i<trow.size();i++) {
	
			
			List<WebElement> tdata = contacts.TableData();
			for(int j=0;j<tdata.size();j++) {
				WebElement w1 =tdata.get(j);
				 System.out.println(w1.getText());
			}
			
			logger.info("************** Printing the WebTable Data ***************");
				}
	
		driver.navigate().back();
		Thread.sleep(3000);
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		
		String path = "S:\\New folder\\projectworkplace\\TestNG-Hubspot\\src\\main\\java\\com\\qa\\Testdata\\testngdatadrivenmar2020.xlsx";
		
		int rowcount = ExcelUtils.getRowCount(path, "Login");
		int colcount = ExcelUtils.getCellCount(path, "Login",1);
		String LoginData[][]=new String[rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<colcount;j++) {
				
				LoginData[i-1][j]= ExcelUtils.getCellData(path, "Login", i,j);
			}
		}
		
		return LoginData;
		
	}
	@Severity(SeverityLevel.CRITICAL)	
	@Description("Data driven from Excel.......")
	@Epic("EP005")
	@Feature("Feature5:Data driven from Excel.")
	@Story("Story:  Data driven from Excel.")
	@Step("Verifying Data driven from Excel.")
	@Test(priority=5 , dataProvider="LoginData" , enabled=true)
	public void LoginDataExcel(String email ,String firstname, String lastname, String jobtitle) throws InterruptedException {
		
		ContactsPage contacts = new ContactsPage(driver);
	contacts.ClickcontactLink().click();
	contacts.ClickcontactBtn().click();
		Thread.sleep(5000);
		contacts.Createcontact().click();
		Thread.sleep(2000);
		contacts.contactEmail().sendKeys(email);
		contacts.contactFname().sendKeys(firstname);
		contacts.contactLname().sendKeys(lastname);
		contacts.contactJobtitle().sendKeys(jobtitle);
		
		contacts.createcontactbtn().click();
		Thread.sleep(6000);
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		
		logger.info("************** Creating the new contacts using Data driven And Data Provider Methods ***************");
	}
	
}
