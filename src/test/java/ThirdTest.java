import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Created by User on 21.11.2018.
 Написать тест, который:
 Ищет какое-то слово
 Убеждается, что в каждом результате поиска есть это слово.
 */
public class ThirdTest extends CoreTestCase {
    private static String searchText = "sun";
    private SearchPageObject searchPageObject;

    @Test
    public void testSearchResults() {
       searchPageObject = new SearchPageObject(driver);
       searchPageObject.initSearchInput();
       searchPageObject.typeSearchLine(searchText);
       List<WebElement> list = searchPageObject.waitForSearchResultsTitles();
       for (WebElement element: list) {
           String text = element.getAttribute("text").toString().toLowerCase();
           assertTrue("there is no searchTextInTheResults", text.contains(searchText.toLowerCase()));
           System.out.println("word "+ searchText + " presents in the text " + text);
       }
    }
}
