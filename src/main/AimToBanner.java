package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class AimToBanner {
	
	public void aimToBanner(WebDriver driver) throws Exception {
		String  adfoxLoginPage = "https://login.adfox.ru/";
		String bannerPage = "https://login.adfox.ru/banners.php?campaignID=852894";
		By loginField  = By.xpath("//input[@id='loginAccount']");
		By passwordField  = By.xpath("//input[@name='loginPassword']");
		By buttonLogin = By.xpath("//input[@src='pics/bbrightarrow.gif'] ");
		By buttonAimOnBannerPage = By.xpath("//img[@src='pics/mbshow.gif']");
		By buttonAimFinal= By.xpath("//span[@class='btn btn-icon btn-show']");
		
		driver.get(adfoxLoginPage);
		WebElement loginFieldElement  = driver.findElement(loginField);
		loginFieldElement.clear();
		loginFieldElement.sendKeys("imho-video");
		WebElement passwordFieldElement  = driver.findElement(passwordField);
		passwordFieldElement.clear();
		passwordFieldElement.sendKeys("reason-suRRouNdeD-importaNce");
		driver.findElement(buttonLogin).click();
		driver.get(bannerPage);
		((JavascriptExecutor) driver).executeScript("scroll(0,800)");
		driver.findElement(buttonAimOnBannerPage).click();
		WebDriverWait waitG = new WebDriverWait(driver, 10);		
		waitG.until(ExpectedConditions.visibilityOfElementLocated(buttonAimFinal));
		driver.findElement(buttonAimFinal).click();
		Thread.sleep(3000);
		
		
	}

}
