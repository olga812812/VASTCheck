package main;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AimToBannerPage extends BasePage {
	
	public AimToBannerPage(WebDriver driver) {
		super(driver);
	}
	
	public void aimToBanner() throws Exception {
		String bannerID = getProperty("bannerID");
		String campaignID = getProperty("fligthID");;
		String  adfoxLoginPage = "https://login.adfox.ru/";
		String bannerPage = "https://login.adfox.ru/banners.php?campaignID="+campaignID;
		By loginField  = By.xpath("//input[@id='loginAccount']");
		By passwordField  = By.xpath("//input[@name='loginPassword']");
		By buttonLogin = By.xpath("//input[@src='pics/bbrightarrow.gif'] ");
		By buttonAimOnBannerPage = By.xpath("//a[contains(@onclick, '"+bannerID+"')]/img[@src='pics/mbshow.gif']");
		By buttonAimFinal= By.xpath("//span[@class='btn btn-icon btn-show']");
		
		driver.get(adfoxLoginPage);
		Cookie cookie  = new Cookie("luid1", "g:bqnawex:g:bqnawex:a","adfox.ru","/",null,true);	
		Cookie cookie1 = new Cookie("cryptouid", "14410810835107987422","adfox.ru","/",null,true);
		Cookie cookie2 = new Cookie("cryptouid_sign", "81b07cae447424e5bf0ecfe36c4609f0","adfox.ru","/",null,true);
		Cookie cookie3 = new Cookie("cryptouid_actual", "1","adfox.ru","/",null,true);
		driver.manage().addCookie(cookie);
		driver.manage().addCookie(cookie1);
		driver.manage().addCookie(cookie2);
		driver.manage().addCookie(cookie3);
		driver.get(adfoxLoginPage);
		WebElement loginFieldElement  = driver.findElement(loginField);
		loginFieldElement.clear();
		loginFieldElement.sendKeys(getProperty("adfoxLogin"));
		WebElement passwordFieldElement  = driver.findElement(passwordField);
		passwordFieldElement.clear();
		passwordFieldElement.sendKeys(getProperty("adfoxPassword"));
		driver.findElement(buttonLogin).click();
		driver.get(bannerPage);
		scrollPageToPixels(800);
		driver.findElement(buttonAimOnBannerPage).click();
		waitVisibilityOfElement(buttonAimFinal);
		driver.findElement(buttonAimFinal).click();
		Thread.sleep(3000);		
		
	}

}
