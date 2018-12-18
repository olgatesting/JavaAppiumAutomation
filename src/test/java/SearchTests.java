import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by User on 15.12.2018.
 */
public class SearchTests extends CoreTestCase {
    private SearchPageObject searchPageObject;
    private static String searchText;
    private static String title;
    private static String description;

    @Test
    public void testSearchByTitleAndDescription() {
        searchPageObject = new SearchPageObject(driver);
        searchText = "Java";
        title = "Java (programming language)";
        description = "Object-oriented programming language";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.assertMoreThenKElementsPresent(3);
        searchPageObject.waitForElementByTitleAndDescription(title, description);
    }
    @Test
    public void testSearch()
    {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.assertTextSearchPresentsInTheSearchLine("Search…");
    }

    @Test
    public void testSearchResultsDisappear() {
        searchPageObject = new SearchPageObject(driver);
        searchText = "lego";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.assertMoreThenKElementsPresent(1);
        searchPageObject.waitForSearchLineAndClear();

        assertTrue(
                searchPageObject.waitForSearchResultsDisappear());
    }

    @Test
    public void testSearchResults() {
        searchPageObject = new SearchPageObject(driver);
        searchText = "sun";
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
