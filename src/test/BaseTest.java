package test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.*;
import java.util.logging.Level;

import main.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
	
	static WebDriver driver = initDriver("Firefox");
	
			
public static WebDriver initDriver(String browserName) {
		
	switch (browserName) {
		case "Chrome": {
			BasePage.setBrowser(new ChromeBrowser());
			return driver = new ChromeDriver(defineSettingForLogsChrome());
		}
		case "Firefox": {
			BasePage.setBrowser(new FirefoxBrowser());
			return new FirefoxDriver(defineSettingForLogsFirefox());
		}
		case "Opera": {
			BasePage.setBrowser(new OperaBrowser());
			return new OperaDriver(defineSettingForLogsOpera());
		}
		default: {
			BasePage.setBrowser(new ChromeBrowser());
			return driver = new ChromeDriver(defineSettingForLogsChrome());
		}
		}	
		
	}
	
	public static ChromeOptions defineSettingForLogsChrome() {
		WebDriverManager.chromedriver().setup();
		LoggingPreferences preferences = new LoggingPreferences();
		preferences.enable(LogType.PERFORMANCE, Level.ALL);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, preferences);		
		ChromeOptions options = new ChromeOptions();
		options.merge(capabilities);
		return options;
		
	}
	
	public static GeckoDriverService defineSettingForLogsFirefox() {	
		System.setProperty("webdriver.gecko.driver", "C:\\firefoxDriver\\geckodriver.exe");
		Map<String, String> environment = new HashMap<String, String>();
		environment.put("MOZ_LOG", "timestamp,rotate:2048,nsHttp:3");
		environment.put("MOZ_LOG_FILE", "C:\\firefoxDriver\\log.txt");
		GeckoDriverService service = new GeckoDriverService.Builder()
		        .usingAnyFreePort()
		        .withEnvironment(environment)
		        .build();
		return service;
		
	}
	
	public static OperaOptions defineSettingForLogsOpera() {
		WebDriverManager.operadriver().setup();
		LoggingPreferences preferences = new LoggingPreferences();
		preferences.enable(LogType.PERFORMANCE, Level.ALL);
		DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, preferences);
		OperaOptions options = new OperaOptions();
		options.merge(capabilities);
		return options;
		
	}

}
