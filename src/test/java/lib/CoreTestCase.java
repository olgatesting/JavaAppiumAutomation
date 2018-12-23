package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 12.12.2018.
 */
public class CoreTestCase extends TestCase{
        private static final String
        PLATFORM_IOS="ios",
        PLATFORM_ANDROID = "android";

        protected static AppiumDriver driver;
        private String AppiumURL ="http://127.0.0.1:4723/wd/hub";

        @Override
        protected void setUp() throws Exception {
            super.setUp();
            DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
            driver = this.getDriverByPlatformEnv(capabilities);
        }
        @Override
        protected void tearDown() throws Exception {
            driver.quit();
            super.tearDown();
        }

    protected void rotateScreenPortrait() {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int sec) {
        driver.runAppInBackground(sec);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws  Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "C:\\Users\\User\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
            capabilities.setCapability("orientation", "PORTRAIT");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "C:\\Users\\User\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\Wikipedia.app");
            rotateScreenPortrait();
        } else {
            throw new Exception("can not get run platform from env variable. Platform value" + platform);
        }
       return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(DesiredCapabilities capabilities) throws Exception {

        String platform = System.getenv("PLATFORM");
        AppiumDriver driver;
        if (platform.equals(PLATFORM_ANDROID)) {
            driver =  new AndroidDriver(new URL(AppiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            driver =  new IOSDriver(new URL(AppiumURL), capabilities);
            } else {
            throw new Exception("can not get run platform from env variable. Platform value" + platform);
        }
        return driver;
    }
    }

