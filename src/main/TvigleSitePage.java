package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TvigleSitePage extends BasePage implements SitePageBehavior {
	By vastPleerFrame = By.xpath("//iframe[@id='tvPlayerObj0']");
	By buttonPlay = By.xpath("//div[@id='play']/i");
	
	public TvigleSitePage(WebDriver driver) {
		super(driver);
	}

	public void clickPlayInPleer() {
		driver.switchTo().frame(driver.findElement(vastPleerFrame));
		waitVisibilityOfElement(buttonPlay);
		driver.findElement(buttonPlay).click();		
	}
	
	public int getPixelsForScrollToPleer() {
		return 0;
	}

}
