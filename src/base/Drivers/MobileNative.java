package base.Drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class MobileNative implements Driver {

	private Logger log;
	AndroidDriver<AndroidElement> driver;
	
	public MobileNative(){
		log = LogManager.getLogger(this.getClass().getName());

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "30");

		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.fivemobile.thescore");
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.fivemobile.thescore.SplashActivity");

		try{
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap );
		}catch (MalformedURLException mue){
			log.error("Cannot resolve Appium URL");
			driver = null;
		}

	}
	@Override
	public AndroidDriver<AndroidElement> createDriver() {
		return driver;
		
	}

}
