package pages.googleVoice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class GoogleVoicePage extends BasePage{
	final String TD_BANK_VERIFICATION_CODE = "Your TD Bank verification code is";
	public GoogleVoicePage(WebDriver driver){
		super(driver);
		super.setPropertiesFile("GoogleVoice.properties");
	}
	
	public void clickSignIn(){
		helpers.elementClick(properties.getProperty("sign_in"), "xpath");

	}
	
	public void login(String email, String password){
		helpers.waitForElementVisible(properties.getProperty("login_email"), "xpath");
		helpers.sendKeys(properties.getProperty("login_email"), "xpath", email);
		helpers.elementClick(properties.getProperty("login_next"), "xpath");
		
		helpers.waitForElementVisible(properties.getProperty("login_password"), "xpath");
		helpers.sendKeys(properties.getProperty("login_password"), "xpath", password);
		helpers.elementClick(properties.getProperty("password_next"), "xpath");
	}
	
	public String getFirstMessage(){
		String passcode = null;
		
		helpers.waitForElementVisible(properties.getProperty("gv_conversation_list"), "xpath");
		
		helpers.waitForElementVisible(properties.getProperty("gv_first_message"), "xpath");		
		WebElement firstMsg = helpers.getElement(properties.getProperty("gv_first_message"), "xpath");
		
		String[] contents = firstMsg.getText().split(":");
		if(contents[0].equals(this.TD_BANK_VERIFICATION_CODE)){
			passcode = contents[1].trim();
		}else{
			log.error("No TD Bank verification code");
		}
		
		return passcode;
	}
	
}
