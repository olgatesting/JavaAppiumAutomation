package lib;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by User on 12.12.2018.
 */
public class CoreTestCase extends TestCase{
        protected static AndroidDriver driver;
        private String AppiumURL ="http://127.0.0.1:4723/wd/hub";

        @Override
        protected void setUp() throws Exception {
            super.setUp();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "C:\\Users\\User\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
            capabilities.setCapability("orientation", "PORTRAIT");

            driver = new AndroidDriver(new URL(AppiumURL), capabilities);

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
    }

