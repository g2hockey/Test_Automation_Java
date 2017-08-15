package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import base.WebDriverFactory;
import pages.googleVoice.GoogleVoicePage;

public class test_browser {
	
	
	public static void main(String[] args) {
		
		WebDriverFactory factory = new WebDriverFactory("chrome");
		WebDriver driver = factory.getDriver();
		
		if (driver == null){
			System.out.println("no browser driver");
			return;
		}
		driver.get("http://voice.google.com");
		
	
		GoogleVoicePage gvPage = new GoogleVoicePage(driver);
		gvPage.clickSignIn();
		
		gvPage.login("tdmobileqa1@gmail.com", "mobileqa1234");
		
		String passcode = gvPage.getFirstMessage();
		System.out.println("Lastest passcode: " + passcode);
		
		storePasscode(passcode);
	    
		driver.close();

		
	}

	public static void storePasscode(String passcode){
		try{
		    Path fname = Paths.get(System.getProperty("user.dir"), "passcode.txt");
		    if (Files.notExists(fname)){
		    	Files.createFile(fname);
		    }
		    Files.write(fname, passcode.getBytes());
		    
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}
}
