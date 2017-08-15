package base.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowser implements Driver {

	WebDriver driver;
	
	public FirefoxBrowser(){
		FirefoxProfile ffprofile = new FirefoxProfile();
		ffprofile.setPreference("dom.webnotifications.enabled", false);
		ffprofile.setPreference("app.update.auto", false);
		ffprofile.setPreference("app.update.enabled", false);
		ffprofile.setPreference("app.update.silent", true);

		FirefoxOptions options = new FirefoxOptions().setProfile(ffprofile);
		driver = new FirefoxDriver(options);
	}
	@Override
	public WebDriver createDriver() {
		//Maximize() causes geckodriver to crash in some versions
		//driver.manage().window().maximize();
		return driver;
		
	}

}
