import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

/**
 * Created by User on 12.11.2018.
 */
public class FirstTest extends CoreTestCase{
    private SearchPageObject searchPageObject;
    @Test
    public void testSearch()
    {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.assertTextSearchPresentsInTheSearchLine("Search…");
    }

}
