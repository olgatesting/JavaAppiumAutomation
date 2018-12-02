/**
 * Created by User on 21.11.2018.
 * Написать тест, который:
 1)Ищет какое-то слово
 2)Убеждается, что найдено несколько статей
 3)Отменяет поиск
 4)Убеждается, что результат поиска пропал
 */

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/**
 * Created by User on 12.11.2018.
 */
public class SecondTest  extends TestBase{
    private String wikiSearchXPath = "//*[contains(@text,'Search Wikipedia')]";
    private String searchLineId ="org.wikipedia:id/search_src_text";
    private String searchResultXPath = "//*[contains(@resource-id,'org.wikipedia:id/page_list_item_container')]";

    @Test
    public void secondTest() {
        waitForWikiSearchAndClick(By.xpath(wikiSearchXPath));
        waitForSearchLineAndEnterText(By.id(searchLineId), "lego");

        checkIfMoreThenOneSearchResultPresent(By.xpath(searchResultXPath), "Search result are not found or only one");
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).clear();

        Assert.assertTrue(
               waitForElementDisappear(driver.findElements(By.xpath(searchResultXPath)),
                       "search results do not disappear", 10));
    }

    private void checkIfMoreThenOneSearchResultPresent(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(errorMessage);
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        System.out.println("There are "+list.size() + " results on the page");
        Assert.assertTrue(list.size()>1);
    }

    private void waitForWikiSearchAndClick(By by, String errorMessage, long timeoutInSeconds) {
        waitForElementAndClick(by, errorMessage,timeoutInSeconds);
    }

    private void waitForWikiSearchAndClick(By by) {
        waitForWikiSearchAndClick(by, "No such element", 10);
    }

    private void waitForSearchLineAndEnterText(By by, String errorMessage, long timeoutInSeconds, String text) {
        waitForElementAndEnterText(by,errorMessage,timeoutInSeconds,text);
    }

    private void waitForSearchLineAndEnterText(By by, String text) {
        waitForSearchLineAndEnterText(by, "element not found", 10, text);
    }

}