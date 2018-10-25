package test;

import main.PleerPage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PleerPageTests {
	PleerPage page = new PleerPage();
	
	@DataProvider	
	public Object[][] sitesList() {
		return new Object[][] {{"https://www.1tv.ru/shows/golos-7"}, 
				               {"https://www.1tv.ru"},
				               {"https://megogo.ru/ru/view/95441-zhizn-prekrasna.html"}};
	}
	
	
	@Test(dataProvider="sitesList")
	public void checkVast(String siteUrl) throws Exception {		
		page.loadPageWithPleer(siteUrl);
		page.checkImpression();	
		
		////iframe[@id='tvPlayerObj0']
		//required_frame = driver.find_element_by_xpath("//iframe[contains(@src,'https://www.youtube.com')]")
		//driver.switch_to.frame(required_frame)
		//element = driver.find_element_by_xpath("//button[@aria-label='Play']")
		//element.click()
	}


}