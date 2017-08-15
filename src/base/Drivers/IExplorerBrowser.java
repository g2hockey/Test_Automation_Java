package base.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IExplorerBrowser implements Driver {

	WebDriver driver;
	
	public IExplorerBrowser(){
		driver = new InternetExplorerDriver();
	}
	@Override
	public WebDriver createDriver() {
		driver.manage().window().maximize();
		return driver;
		
	}

}
