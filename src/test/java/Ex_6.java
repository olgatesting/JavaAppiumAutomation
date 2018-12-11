import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by User on 11.12.2018.
 * Написать тест, который открывает статью и убеждается, что у нее есть элемент title. Важно: тест не должен дожидаться
 * появления title, проверка должна производиться сразу. Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
 */
public class Ex_6  extends TestBase{
    private static String searchText = "Java";
    private String articlesTitlesID = "org.wikipedia:id/view_page_title_text";
    @Test
    public void assertElementPresent() {
        waitForWikiSearchAndClick();
        waitForSearchLineAndEnterText(searchText);
        List<WebElement> searchResults = waitForSearchResultsList();
        waitForElementsAndClickOnTheItem(searchResults,0);
        Assert.assertTrue(assertArticleTitlePresent());
    }

    protected boolean assertArticleTitlePresent() {
        try {
            driver.findElement(By.id(articlesTitlesID));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Article title not found");
          //  e.printStackTrace();
            return false;
        }
    }
}