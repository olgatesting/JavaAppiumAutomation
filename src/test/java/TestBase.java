import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by User on 22.11.2018.
 */
public class TestBase extends SetDriver
{
    protected String wikiSearchXPath = "//*[contains(@text,'Search Wikipedia')]";
    protected String searchLineId ="org.wikipedia:id/search_src_text";
    protected String searchResultXPath =
            "//*[contains(@resource-id,'org.wikipedia:id/search_results_list')]/*[contains(@resource-id,'org.wikipedia:id/page_list_item_container')]";

    protected void waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }
    protected void waitForElementAndClick(By by) {
        waitForElementAndClick(by, "no such element as " + by.toString(), 5);
    }

    protected void waitForElementSearchAndClick(By by) {
        waitForElementAndClick(by, "No such element "+ by.toString(), 5);
    }

    protected void waitForElementAndEnterText(By by, String errorMessage, long timeoutInSeconds, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).sendKeys(text);
    }

    protected void waitForElementAndEnterText(By by, String text) {
        waitForElementAndEnterText(by, "element not founs", 10, text);
    }

    protected boolean waitForElementsDisappear(List<WebElement> list, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfAllElements(list));
    }
    protected boolean waitForElementDisappear (By by, String error_message, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void waitForWikiSearchAndClick(By by, String errorMessage, long timeoutInSeconds) {
        waitForElementAndClick(by, errorMessage,timeoutInSeconds);
    }

    protected void waitForWikiSearchAndClick() {
        waitForWikiSearchAndClick(By.xpath(wikiSearchXPath), "No such element " + By.xpath(wikiSearchXPath).toString(), 5);
    }

    protected void waitForSearchLineAndEnterText(By by, String errorMessage, long timeoutInSeconds, String text) {
        waitForElementAndEnterText(by,errorMessage,timeoutInSeconds,text);
    }

    protected void waitForSearchLineAndEnterText(String text) {
        waitForSearchLineAndEnterText(By.id(searchLineId), By.id(searchLineId).toString()+" element not found", 5, text);
    }

    protected List<WebElement> waitForSearchResultsList() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage("search results not present");
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(searchResultXPath)));
        if (list.size()==0) {
            Assert.fail("Null results found on the request");
        }
        System.out.println("There are "+list.size() + " results on the page");
        return list;
    }
    protected WebElement waitForElementPresents(By by, String error_message){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(by.toString()+ error_message);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    protected WebElement waitForElementPresents(By by, String error_message, long timeOutInSecs){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
        wait.withMessage(by.toString()+ error_message);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    protected WebElement waitForElementPresents(By by){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(by.toString()+ " no such element on the page");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    protected void waitForElementsAndClickOnTheItem(List<WebElement> list,int i, long timeOutInSec){
        if (i>=list.size()) {
            throw new IndexOutOfBoundsException("in the method waitForElementsAndClickOnTheItem insert i more then List od WebElement size");
          //  Assert.fail("in the method waitForElementsAndClickOnTheItem insert i more then List od WebElement size");
        }
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.withMessage(list.get(i).toString() + " is not clickable or not present");
        wait.until(ExpectedConditions.elementToBeClickable(list.get(i)));
        list.get(i).click();
            }

    protected void waitForElementAndClear(By by) {
        waitForElementPresents(by).clear();

    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int) (size.height*0.2);

        action.press(x,start_y).waitAction(timeOfSwipe).moveTo(x,end_y).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int alreadySwiped=0;
        while (driver.findElements(by).size()==0) {
            if (alreadySwiped>max_swipes) {
                waitForElementPresents(by, "can't find element with swipe.\n" + error_message, 0 ) ;
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }

    }
    protected void swipeElementToLeft (By by, String error_message) {
        WebElement element = waitForElementPresents(by,
               error_message ,
                10);
        int left_x = element.getLocation().getX();
        int rigth_x = left_x+ element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y+element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action.
                press(rigth_x, middle_y).
                waitAction(400).
                moveTo(left_x, middle_y).
                release().
                perform();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element title not found");
            //  e.printStackTrace();
            return false;
        }
    }

    protected String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresents(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }
//        driver.rotate(ScreenOrientation.PORTRAIT);
//        driver.rotate(ScreenOrientation.LANDSCAPE);

    // driver.runAppInBackground(5);
}
