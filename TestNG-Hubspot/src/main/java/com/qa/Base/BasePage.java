package com.qa.Base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.config.Readconfig;

public class BasePage {
	Readconfig config = new Readconfig();
	public String URL = config.ApplicationUrl(); // from read config and thats from config.properties
	public  WebDriver driver; // webdriver instantziation

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); // webdriver for multiple threads

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public WebDriver init_driver() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"S:\\New folder\\projectworkplace\\TestNG-Hubspot\\driver\\chromedriver.exe");
//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		tlDriver.set(driver);
		Thread.sleep(5000);
		return getDriver();

	}

//@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(6000);
		driver.quit();
	}

	public String getScreenshot() throws IOException {

		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

}
