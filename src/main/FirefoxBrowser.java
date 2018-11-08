package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;


public class FirefoxBrowser implements BrowserBehavior {
	
	ArrayList<String> allRequestsToAdfox;

	
	public void checkLogs(WebDriver driver) {
		
		System.out.println("Hello i am Firefox checkLogs function!!!");
		getRequestsToAdfoxFromLogFile();
		
		
	}

	public void getRequestsToAdfoxFromLogFile() {
		allRequestsToAdfox = new ArrayList<String>();
		
		File logFile = new File("C:\\firefoxDriver\\log.txt.0");
		try {
		Scanner logFileScanner = new Scanner(logFile);
		String logLine;
		while (logFileScanner.hasNext()) {
			logLine=logFileScanner.next();
			if(logLine.contains("puid30")) allRequestsToAdfox.add(logLine);
		}
		logFileScanner.close();
		} catch (Exception e) {}	
		for (int i=0; i<allRequestsToAdfox.size(); i++) {
			System.out.println(allRequestsToAdfox.get(i));
		}
		
	}

	public void checkImpression() {
		Boolean impressionIsHere = false;
		for (int i=0; i<allRequestsToAdfox.size(); i++) {
			if(allRequestsToAdfox.get(i).contains("pm=b")) impressionIsHere=true;
		}
		
		if(impressionIsHere)  System.out.println("Impression is here, OK!!!");
		else System.out.println("Impression is NOT here, Epic Fail!!!");
		
	}

}
