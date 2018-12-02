/**
 * Created by User on 21.11.2018.
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
    @FindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchField;

    private String wikiSearchXPath = "//*[contains(@text,'Search Wikipedia')]";
    private String searchLineId ="org.wikipedia:id/search_src_text";
    private String searchResultXPath = "//*[contains(@resource-id,'org.wikipedia:id/page_list_item_container'])";

    @Test
    public void secondTest() {
        waitForWikiSearchAndClick(By.xpath(wikiSearchXPath));
        waitForSearchLinetAndEnterText(By.id(searchLineId), "lego");
        checkIfMoreThenOneSearchResultPresent(By.xpath(searchResultXPath), "Search result are not found or only one");
        searchField.clear();

        Assert.assertTrue(
               waitForElementDisappear(driver.findElements(By.xpath(searchResultXPath)),
                       "search results do not disappear", 10));
    }

    private void checkIfMoreThenOneSearchResultPresent(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(errorMessage);
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).size()>1);
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

    private void waitForSearchLinetAndEnterText(By by, String text) {
        waitForSearchLineAndEnterText(by, "element not found", 10, text);
    }

}