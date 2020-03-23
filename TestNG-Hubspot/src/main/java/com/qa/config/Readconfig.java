package com.qa.config;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

public class Readconfig {

			Properties prop;
	
	public Readconfig() {
		//Reading the configuration file from configuration folder
		File src= new File("S:\\New folder\\projectworkplace\\TestNG-Hubspot\\configuration\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	//creating method for config.properties values
	
	public String Browsername() {
		String browser = prop.getProperty("browser");
		return browser;
	}
	
	public String ApplicationUrl() {
		String url = prop.getProperty("url");
		return url;
	}
	
	public String Appusername() {
		String user = prop.getProperty("username");
		return user;
	}
	public String AppPassword() {
		String pwd = prop.getProperty("password");
		return pwd;
	}
	
	
	
	
	
	
}
