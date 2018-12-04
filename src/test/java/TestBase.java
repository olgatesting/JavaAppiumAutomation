import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

/**
 * Created by User on 22.11.2018.
 */
public class TestBase extends SetDriver
{

    protected void waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    protected void waitForElementSearchAndClick(By by) {
        waitForElementAndClick(by, "No such element", 10);
    }

    protected void waitForElementAndEnterText(By by, String errorMessage, long timeoutInSeconds, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).sendKeys(text);
    }

    protected void waitForElementAndEnterText(By by, String text) {
        waitForElementAndEnterText(by, "element not founs", 10, text);
    }

    protected boolean waitForElementDisappear(List<WebElement> list, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfAllElements(list));
    }

    protected void waitForWikiSearchAndClick(By by, String errorMessage, long timeoutInSeconds) {
        waitForElementAndClick(by, errorMessage,timeoutInSeconds);
    }

    protected void waitForWikiSearchAndClick(By by) {
        waitForWikiSearchAndClick(by, "No such element", 10);
    }

    protected void waitForSearchLineAndEnterText(By by, String errorMessage, long timeoutInSeconds, String text) {
        waitForElementAndEnterText(by,errorMessage,timeoutInSeconds,text);
    }

    protected void waitForSearchLineAndEnterText(By by, String text) {
        waitForSearchLineAndEnterText(by, "element not found", 10, text);
    }
}
