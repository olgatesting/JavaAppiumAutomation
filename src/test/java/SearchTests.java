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
    @Test
    public void testSearch()
    {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.assertTextSearchPresentsInTheSearchLine("Search…");
    }


    @Test
    public void testSearchResultsDisappear() {
        searchText = "lego";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.checkIfMoreThenOneSearchResultPresent();
        searchPageObject.waitForSearchLinrAndClear();

        assertTrue(
                searchPageObject.waitForSearchREsultsDisappear());
    }

    @Test
    public void testSearchResults() {
        searchText = "sun";
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
