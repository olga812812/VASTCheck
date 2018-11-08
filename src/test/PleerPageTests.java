package test;

import main.*;
import org.testng.annotations.*;

public class PleerPageTests extends BaseTest{
	PleerPage page = new PleerPage(driver);
	AimToBannerPage aimToBannerObj = new AimToBannerPage(driver);
	
	@DataProvider	
	public Object[][] sitesList() {
		return new Object[][] {
				 			 //  {"https://www.1tv.ru/shows/golos-7", new FisrtTVSitePage(driver)}, 
				              // {"https://www.1tv.ru", new PageNoSpecialties(driver)},
				             //  {"https://megogo.ru/ru/view/4486-priklyucheniya-krosha.html", new MegogoSitePage(driver)},
				               {"https://www.tvigle.ru/video/intouchables/", new TvigleSitePage(driver)}};
	}
	
	@BeforeTest
    public void setUp() throws Exception {
		aimToBannerObj.aimToBanner();	
	}

	
	@Test(dataProvider="sitesList")
	public void checkBanner(String siteUrl, SitePageBehavior sitePage) throws Exception {	
			
		page.loadPageWithPleer(siteUrl, sitePage);
		page.checkLogs();
		page.checkImpression();		
	}
	

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}