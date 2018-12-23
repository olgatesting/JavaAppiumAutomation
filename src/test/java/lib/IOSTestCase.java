package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by User on 19.12.2018.
 */
public class IOSTestCase extends TestCase{
    protected static IOSDriver driver;
    private String AppiumURL ="http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "/User/Documents/GitHub/JavaAppiumAutomation/apks/Wikipedia.app");
       // capabilities.setCapability("orientation", "PORTRAIT");

        driver = new IOSDriver(new URL(AppiumURL), capabilities);

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

