package pages;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import base.SeleniumHelpers;

public class BasePage {
	protected WebDriver driver;
	protected SeleniumHelpers helpers;
	protected String PROJECT_ROOT_PATH;
	protected Properties properties;
	protected Logger log;


	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.helpers = new SeleniumHelpers(driver);
		PROJECT_ROOT_PATH = System.getProperty("user.dir");
		this.log = LogManager.getLogger(this.getClass().getName());

	}

	public void setPropertiesFile(String fname){
		try{
			Path propPath = Paths.get(this.PROJECT_ROOT_PATH, "src", "properties", fname);
			FileReader reader = new FileReader(propPath.toFile());

			properties = new Properties();
			properties.load(reader);
		}catch(Exception e){
			log.error("Cannot load properties file: " + fname);
		}
	}
}
