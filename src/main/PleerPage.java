package main;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PleerPage {
    private WebDriver driver;  
    Map<String, String> adfoxRequests;
    Map<String, String> responsesForAdfoxRequests;
	
	
	
	public void loadPageWithPleer(String pageUrl, ClickToPlayVideo clickToPlayVideo) throws Exception {	
		loadDriver();
		driver.get(pageUrl);		
		((JavascriptExecutor) driver).executeScript("scroll(0,0)");
		clickToPlayVideo.clickPlayInPleer(driver);
		Thread.sleep(20000);
		checkLogs();
		driver.quit();
		
	}
	
	public void loadDriver() {
		
		driver = new ChromeDriver(defineSettingForLogs());
		
	}
	
	public ChromeOptions defineSettingForLogs() {
		WebDriverManager.chromedriver().setup();
		LoggingPreferences preferences = new LoggingPreferences();
		preferences.enable(LogType.PERFORMANCE, Level.ALL);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, preferences);
		ChromeOptions options = new ChromeOptions();
		options.merge(capabilities);
		return options;
		
	}
	
	public void checkLogs() {		
		LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);		
		getAllRequestsToAdfoxAndResponsesForThemFromLogs(logs);	
		printAdfoxRequestsAndResponses();
	}
	
	public void getAllRequestsToAdfoxAndResponsesForThemFromLogs(LogEntries logs) {
		String adfoxRequestIdFromLog="unreacheble";
		String logLine;
		int startAdfoxRequestIndex, endAdfoxRequestIndex;
		String getCodeRegex, eventRegex;	
		adfoxRequests = new HashMap<String, String>();	
		responsesForAdfoxRequests = new HashMap<String, String>();
		String adfoxRequestSearchString="url\":\"";
		
	  for (LogEntry entry : logs) {
			getCodeRegex=".*Network.requestWillBeSent.*226279/getCode.*puid30.*";
			eventRegex=".*Network.requestWillBeSent.*226279/event.*puid30.*";
			logLine = entry.toString();
		
		if (logLine.matches(getCodeRegex)||logLine.matches(eventRegex)) {
			startAdfoxRequestIndex=logLine.indexOf(adfoxRequestSearchString+"https://v.adfox.ru/226279/")+adfoxRequestSearchString.length();
			endAdfoxRequestIndex=logLine.indexOf("\"},\"requestId");				
			adfoxRequestIdFromLog = getRequestId(logLine); 
		 	adfoxRequests.put(adfoxRequestIdFromLog, logLine.substring(startAdfoxRequestIndex,endAdfoxRequestIndex));
		}
		for (String keyAdfoxRequestIdFromLog: adfoxRequests.keySet()) {
		  if(logLine.matches(".*Network.responseReceived.*"+keyAdfoxRequestIdFromLog+".*")) {
			 int startStatusIndex;
			 int responseLength = 40;
			 Pattern responseRegex = Pattern.compile("\"status\":([0-9]{3}),\"statusText");  
			 Matcher m = responseRegex.matcher(logLine);
			 if (m.find()) {
			    startStatusIndex = m.start();
			    responsesForAdfoxRequests.put(keyAdfoxRequestIdFromLog, logLine.substring(startStatusIndex, startStatusIndex+responseLength));
			 }			
		  }
		}
	  }
		
	}
	
	public String getRequestId (String str) {
		 int requestIdLength = 15;
		 String requestIdSearchString = "\"requestId\":\"";
		 int startRequestIdIndex = str.indexOf(requestIdSearchString) + requestIdSearchString.length();		
		 return str.substring(startRequestIdIndex,startRequestIdIndex+requestIdLength); 
		
	}
	
	public void printAdfoxRequestsAndResponses() {
		for (String adfoxRequestId: adfoxRequests.keySet()) {
			System.out.println("Request is: "+adfoxRequests.get(adfoxRequestId));
			System.out.println("Response to it is: "+responsesForAdfoxRequests.get(adfoxRequestId));
		}
	}
	
	public void checkImpression() {
		String requestToAdfox;
		Boolean impressionIsHere = false;
		for (String adfoxRequestId: adfoxRequests.keySet()) {
			requestToAdfox = adfoxRequests.get(adfoxRequestId);
			if (requestToAdfox.contains("pm=b")) impressionIsHere=true;			
		}
		if (impressionIsHere) System.out.println("OK.Impression is here");
		else throw new NullPointerException("There is no Impression Treker!!!");
		
	}
}