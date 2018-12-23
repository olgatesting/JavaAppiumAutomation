import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

/**
 * Created by User on 15.12.2018.
 */
public class MyListsTests extends CoreTestCase {
    private static String searchText;
    private static String readingBookName ;

    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private NavigationUI navigationUI;
    private MyListsPageObject myListsPageObject;

    @Test
    public void testTwoArticlesSaving() {

        searchText = "MayDay";
        readingBookName = "My first list";

        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        navigationUI = new NavigationUI(driver);
        myListsPageObject = new MyListsPageObject(driver);
        searchPageObject.findSearchResultsAndClickOneOfThem(searchText,0);

        String firstArticleTitle = articlePageObject.saveArticleTitle();
        articlePageObject.addArticleToReadingListFirstTime(readingBookName);
        searchPageObject.findSearchResultsAndClickOneOfThem(searchText,2);
        String secondArticleTitle =  articlePageObject.saveArticleTitle();
        articlePageObject.addArticleToReadingList(readingBookName);

        navigationUI.clickMyLists();

        myListsPageObject.openReadingListFolderByName(readingBookName);
        myListsPageObject.SwipeByArticleAndDelete(secondArticleTitle);
        myListsPageObject.openArticleInReadingList(firstArticleTitle);
        String actualArticleTitle = articlePageObject.saveArticleTitle();

        assertTrue("title of saved article and actual presented in the book list article are not equal",actualArticleTitle.equals(firstArticleTitle));
    }
}
