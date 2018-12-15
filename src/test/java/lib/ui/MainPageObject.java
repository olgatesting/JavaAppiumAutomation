package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by User on 12.12.2018.
 */
public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }
    public void waitForElementAndClick(By by) {
        waitForElementAndClick(by, "no such element as " + by.toString(), 5);
    }

    public void waitForElementAndEnterText(By by, String errorMessage, long timeoutInSeconds, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).sendKeys(text);
    }
    public void waitForElementAndEnterText(By by, String text) {
        waitForElementAndEnterText(by, "element not found", 10, text);
    }

    public boolean waitForElementsDisappear(List<WebElement> list, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfAllElements(list));
    }
    public boolean waitForElementDisappear (By by, String error_message, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementPresents(By by, String error_message, long timeOutInSecs){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
        wait.withMessage(by.toString()+ error_message);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public WebElement waitForElementPresents(By by){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(by.toString()+ " no such element on the page");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public void waitForElementAndClear(By by) {
        waitForElementPresents(by).clear();

    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int) (size.height*0.2);

        action.press(x,start_y).waitAction(timeOfSwipe).moveTo(x,end_y).release().perform();
    }
    public void swipeUpQuick() {
        swipeUp(200);
    }
    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
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
    public void swipeElementToLeft (By by, String error_message) {
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

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element title not found");
            //  e.printStackTrace();
            return false;
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresents(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

}
