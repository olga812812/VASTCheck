package main;

import org.openqa.selenium.WebDriver;

public class FisrtTVSitePage extends BasePage implements SitePageBehavior{

	public FisrtTVSitePage(WebDriver driver) {
		super(driver);
		
	}
	
	public void clickPlayInPleer() {}

	
	public int getPixelsForScrollToPleer() {
		return 700;
	}

}
