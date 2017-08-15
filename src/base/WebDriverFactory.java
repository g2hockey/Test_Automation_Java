package base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import base.Drivers.ChromeBrowser;
import base.Drivers.MobileChrome;
import base.Drivers.MobileNative;
import base.Drivers.Driver;
import base.Drivers.DriverTypes;
import base.Drivers.FirefoxBrowser;
import base.Drivers.IExplorerBrowser;

public class WebDriverFactory {

	WebDriver driver;
	Driver manager;
	Logger log;

	public WebDriverFactory(String browser){
		this.log = LogManager.getLogger(this.getClass().getName());

		if(browser.equalsIgnoreCase(DriverTypes.FIREFOX)){
			manager = new FirefoxBrowser();
		}else if(browser.equalsIgnoreCase(DriverTypes.IEXPLORER)){
			manager = new IExplorerBrowser();
		}else if(browser.equalsIgnoreCase(DriverTypes.CHROME)){
			manager = new ChromeBrowser();
		}else if(browser.equalsIgnoreCase(DriverTypes.MOBILE_BROWSER)){
			manager = new MobileChrome();
		}else if(browser.equalsIgnoreCase(DriverTypes.MOBILE_APP)){
			manager = new MobileNative();
		}else{
			manager = null;
			log.error("Cannot find applicable browser: " + browser);
		}
	}
	
	public WebDriver getDriver(){
		if(manager != null){
			driver = manager.createDriver();
			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
		}else{
			driver = null;
		}
		return driver;
	}
}
