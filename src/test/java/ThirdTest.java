import lib.CoreTestCase;
import lib.ui.MainPageObject;
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
public class ThirdTest extends CoreTestCase {
    private static String searchText = "sun";
    private String searchResultTextsXPath = "//*[contains(@resource-id,'org.wikipedia:id/page_list_item_title')]";
    private MainPageObject mainPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    @Test
    public void testSearchResults() {
        mainPageObject.waitForWikiSearchAndClick();
        mainPageObject.waitForSearchLineAndEnterText(searchText);
        List<WebElement> list = mainPageObject.waitListOfResultsTexts(By.xpath(searchResultTextsXPath),"search results not found");
       for (WebElement element: list) {
           String text = element.getAttribute("text").toString().toLowerCase();
           Assert.assertTrue("there is no searchTextInTheResults", text.contains(searchText.toLowerCase()));
           System.out.println("word "+ searchText + " presents in the text " + text);
       }
    }
}
