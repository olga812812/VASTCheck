package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	 public WebDriver driver;  
	 public static BrowserBehavior browser;
	 
	 public BasePage (WebDriver  driver) {	 
	 this.driver = driver;
	 }
	 
	 public BasePage () { }
	 
	 public void waitVisibilityOfElement(By element){  
	 WebDriverWait wait = new WebDriverWait(driver, 10);		
	 wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	 }
	 
	 public void scrollPageToPixels(int pixels) {
		 ((JavascriptExecutor) driver).executeScript("scroll(0,"+pixels+")");
	 }
	 
	 public static  void setBrowser(BrowserBehavior browser) {
		BasePage.browser=browser;
		 
	 }
 
	 public static String getProperty(String propertyName) {
		 
		 return loadProperties().getProperty(propertyName);
		 
	 }
	 
	 private static Properties loadProperties () {
		Properties properties = new Properties();
		InputStream stream = null;
		InputStreamReader reader = null;

		try {
			 stream = new FileInputStream(new File("CheckVAST.properties"));
		    reader = new InputStreamReader(stream,"Windows-1251");
		    properties.load(reader);
		} catch (Exception ex) {System.out.println(ex);}


		return properties;

		}

}
