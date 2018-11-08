package main;

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
 
}
