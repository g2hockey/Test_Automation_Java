package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelpers {
	WebDriver driver;
	private Logger log;

	public SeleniumHelpers(WebDriver driver) {
		this.driver = driver;
		this.log = LogManager.getLogger(SeleniumHelpers.class.getName());
		
	}
	
	public By getByType(String locator, String type){
		By byType = null;
		type = type.toLowerCase();
		if(type.equals("id")){
			byType = By.id(locator);
		}
		else if(type.equals("name")){
			byType = By.name(locator);
		}
		else if(type.equals("xpath")){
			byType = By.xpath(locator);
		}
		else if(type.equals("css")){
			byType = By.cssSelector(locator);
		}
		else if(type.equals("linktext")){
			byType = By.linkText(locator);
		}
		else if(type.equals("partiallinktext")){
			byType = By.partialLinkText(locator);
		}
		else{
			log.info("Locator type: " + type + " not supported");
		}
		
		return byType;
	}
	public WebElement getElement(String locator, String type){
		WebElement element = null;
		
		try{
			By byElement = this.getByType(locator, type);
			element = driver.findElement(byElement);
			log.info("Element found with " + type + ": " + locator);

		}catch(Exception e){
			log.error("Element not found with " + type + ": "  + locator, e);

		}
		
		return element;

	}
	
	public void elementClick(String locator, String type){
		try{
			WebElement element = this.getElement(locator, type);
			if (element != null){
				element.click();
				log.info("Clicked on element found with " + type + ": " + locator);
			}
		}catch(Exception e){
			log.error("Cannot click on element found with " + type + ": " + locator, e);
		}
	}
	
	public void elementClick(WebElement element){
		try{
			element.click();
			log.info("Clicked on element found with element: " + element.getTagName());

		}catch(Exception e){
			log.error("Cannot click on element found with element: " + element.getTagName(), e);
		}
	}
	
    public void sendKeys(String locator, String type, String data){
    	
    	try{
			WebElement element = this.getElement(locator, type);
			if (element != null){
				element.sendKeys(new String[]{data});
				log.info("Sent data on element with locator: " + locator +
                        " locatorType: " + type);
			}
		}catch(Exception e){
			log.error("Cannot send data on the element with locator: " + locator +
		                  " locatorType: " + type, e);

		}
    }
    
    public void sendKeys(WebElement element, String data){
    	
    	try{

    		element.sendKeys(new String[]{data});
    		log.info("Sent data on element : " + element.getTagName());

    	}catch(Exception e){
    		log.error("Cannot send data on the element : " + element.getTagName(), e);

    	}
    }

    
    public void clearText(String locator, String type){
    	
    	try{
			WebElement element = this.getElement(locator, type);
			if (element != null){
				element.clear();
				log.info("Clear text on element with locator: " + locator +
                        " locatorType: " + type);
			}
		}catch(Exception e){
			log.error("Cannot clear text on the element with locator: " + locator + 
						" locatorType: " + type, e);

		}
    }
 
    public void clearText(WebElement element){

    	try{
    		element.clear();
    		log.info("Clear text on element : " + element.getTagName());

    	}catch(Exception e){
    		log.error("Cannot clear text on the element : " + element.getTagName(), e);
   
    	}
    }

    public void elementSelect(String locator, String type, String data){
    	try{
    		WebElement element = this.getElement(locator, type);
    		Select select = new Select(element);
    		select.selectByVisibleText(data);
    		
    		log.info("Selected option on element with locator: " + locator +
                      " locatorType: " + type);
    	}catch(Exception e){
    		log.error("Cannot select on the element with locator: " + locator + " locatorType: " + type, e);

    	}
    }
        
            
	public boolean isElementPresent(String locator, String type){
		boolean result = false;
        try{
     
        	WebElement element = this.getElement(locator, type);

        	if (element != null){
        		log.info("Element present with locator: " + locator +
                              " locatorType: " + type);
                result = true;
        	}else{
        		log.info("Element not present with locator: " + locator +
                              " locatorType: " + type);
                result = false;
        	}
        }catch(Exception e){
        	log.error("Element not found " + locator, e );
			result = false;
        }
        
        return result;
	}
    
	public boolean isElementDisplayed(String locator, String locatorType){

		boolean isDisplayed = false;
		try{

			WebElement element = this.getElement(locator, locatorType);

			if (element != null){ 
				isDisplayed = element.isDisplayed();
				if (isDisplayed) {
					log.info("Element is displayed with locator: " + locator +
							" locatorType: " + locatorType);
				}else{
					log.info("Element not displayed with locator: " + locator +
							" locatorType: " + locatorType);
				}
			}else{
				log.info("Element not found"  + locator);
				isDisplayed = false;
			}

		}catch(Exception e){
			log.error("Element not found " + locator, e );
			isDisplayed = false;
		}

		return isDisplayed;
	}
            		
	public WebElement waitForElementVisible(String locator, String type){
		WebElement element = null;
		
		try{
			log.info("Waiting for max 15 seconds for element to be available");
			
			By byElement = this.getByType(locator, type);
			WebDriverWait wait = new WebDriverWait(driver,15);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
			
			log.info("Element: " + locator + " found on page");
		}catch(Exception e){
			log.error("Element: " + locator + " not appear on page", e);
		
		}
		
		return element;
		
		
	}

	public WebElement waitForElementVisible(WebElement element){
		WebElement elem = null;
		
		try{
			log.info("Waiting for max 10 seconds for element to be available");
			
			WebDriverWait wait = new WebDriverWait(driver,10);
			elem = wait.until(ExpectedConditions.visibilityOf(element));
			
			log.info("Element: " + element.getText() + " found on page");
		}catch(Exception e){
			log.error("Element not appear on page", e);
		}
		
		return elem;
		
		
	}
	
	public void scrollIntoView(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor)this.driver;
		jse.executeScript("arguments[0].scrollIntoView(true)", element);
	}
}
