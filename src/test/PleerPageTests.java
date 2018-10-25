package test;

import main.ClickToPlayVideo;
import main.NoClick;
import main.PleerPage;
import main.TvigleClickPlayInPleer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PleerPageTests {
	PleerPage page = new PleerPage();
	
	@DataProvider	
	public Object[][] sitesList() {
		return new Object[][] {
				 			   //{"https://www.1tv.ru/shows/golos-7", new NoClick()}, 
				               //{"https://www.1tv.ru", new NoClick()},
				               //{"https://megogo.ru/ru/view/95441-zhizn-prekrasna.html", new NoClick()},
				               {"https://www.tvigle.ru/video/intouchables/", new TvigleClickPlayInPleer()}};
	}
	
	
	@Test(dataProvider="sitesList")
	public void checkVast(String siteUrl, ClickToPlayVideo clickToPlayVideo) throws Exception {		
		page.loadPageWithPleer(siteUrl, clickToPlayVideo);
		page.checkImpression();		
	}


}