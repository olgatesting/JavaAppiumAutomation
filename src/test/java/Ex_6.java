import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

/**
 * Created by User on 11.12.2018.
 * Написать тест, который открывает статью и убеждается, что у нее есть элемент title. Важно: тест не должен дожидаться
 * появления title, проверка должна производиться сразу. Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
 */


public class Ex_6  extends CoreTestCase{
    private static String searchText = "Java";
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    @Test
    public void testTitlePresent() {
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForSearchResultAndClickOneOfThem();
        articlePageObject.assertArticleTitlePresentWithNULLTimeOut();
    }
}
