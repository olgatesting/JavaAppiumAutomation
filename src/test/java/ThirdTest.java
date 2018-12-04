import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by User on 21.11.2018.
 Написать тест, который:
 Ищет какое-то слово
 Убеждается, что в каждом результате поиска есть это слово.
 */
public class ThirdTest extends TestBase{
    private static String searchText = "sun";
    private String wikiSearchXPath = "//*[contains(@text,'Search Wikipedia')]";
    private String searchLineId ="org.wikipedia:id/search_src_text";
    private String searchResultTextsXPath = "//*[contains(@resource-id,'org.wikipedia:id/page_list_item_title')]";
    @Test
    public void checkSearchResultsTest() {
        waitForWikiSearchAndClick(By.xpath(wikiSearchXPath));
        waitForSearchLineAndEnterText(By.id(searchLineId), searchText);
        List<WebElement> list = waitListOfResultsTexts(By.xpath(searchResultTextsXPath),"search results not found");
       for (WebElement element: list) {
           String text = element.getAttribute("text").toString().toLowerCase();
           Assert.assertTrue("there is no searchTextInTheResults", text.contains(searchText.toLowerCase()));
           System.out.println("word "+ searchText + " presents in the text " + text);
       }
    }

    private List<WebElement> waitListOfResultsTexts(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(errorMessage);
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return list;
    }

}
