package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MegogoSitePage extends BasePage implements SitePageBehavior{
	
	By buttonPlay = By.xpath("//div[@id='big_play_button']");

	public MegogoSitePage(WebDriver driver) {
		super(driver);
		
	}

	public void clickPlayInPleer() {
		waitVisibilityOfElement(buttonPlay);
		driver.findElement(buttonPlay).click();
		
	}

	public int getPixelsForScrollToPleer() {
		return 300;
	}
}
