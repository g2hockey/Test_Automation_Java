package base.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements Driver {

	WebDriver driver;
	
	public ChromeBrowser(){
		driver = new ChromeDriver();
	}
	@Override
	public WebDriver createDriver() {
		driver.manage().window().maximize();
		return driver;
		
	}

}
