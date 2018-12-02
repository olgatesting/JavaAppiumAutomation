import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by User on 12.11.2018.
 */
public class FirstTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\User\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities );
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void firstTest()
    {
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        element_to_init_search.click();
        assertTextSearchPresentsInTheSearchLine("Search…");
    }

    private void assertTextSearchPresentsInTheSearchLine (String text) {
        WebElement element_to_enter_search_line = driver.findElementById("org.wikipedia:id/search_src_text");
        String textInTheSearchLine = element_to_enter_search_line.getAttribute("text");
        boolean contains = textInTheSearchLine.contains(text);
        Assert.assertTrue("Required text does not present in the Search line", contains);
    }

}
