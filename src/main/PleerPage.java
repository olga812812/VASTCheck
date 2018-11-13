package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class PleerPage extends BasePage {
   
 
    
    public PleerPage (WebDriver driver) {
    	super(driver);
    }
	
	
	public void loadPageWithPleer(String pageUrl, SitePageBehavior sitePage) throws Exception {	
		int bannerDuration= Integer.parseInt(getProperty("bannerDuration"));
		int loadTime=3000;
		switchToPreviousTab();
		driver.get(pageUrl);		
		scrollPageToPixels(sitePage.getPixelsForScrollToPleer());
		sitePage.clickPlayInPleer();
		Thread.sleep(loadTime+bannerDuration);
				
		
	}
	
	public void switchToPreviousTab() {
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));	
	}
	
	public void checkLogs () {
		browser.checkLogs(driver);	
	}

	
	public void checkImpression() {
		browser.checkImpression();
		
	}
}