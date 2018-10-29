package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TvigleClickPlayInPleer implements ClickToPlayVideo {
	By vastPleerFrame = By.xpath("//iframe[@id='tvPlayerObj0']");
	By buttonPlay = By.xpath("//div[@id='play']/i");


	public void clickPlayInPleer(WebDriver driver) {
		driver.switchTo().frame(driver.findElement(vastPleerFrame));
		WebDriverWait waitG = new WebDriverWait(driver, 10);		
		waitG.until(ExpectedConditions.visibilityOfElementLocated(buttonPlay));
		driver.findElement(buttonPlay).click();
		System.out.println("Click play in pleer!!!");
		
	}

}
