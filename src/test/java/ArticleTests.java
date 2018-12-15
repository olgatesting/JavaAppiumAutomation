import lib.CoreTestCase;

/**
 * Created by User on 15.12.2018.
 */
import lib.ui.*;
import org.junit.Test;

/**
 * Created by User on 05.12.2018.
 Написать тест, который:
 1. Сохраняет две статьи в одну папку
 2. Удаляет одну из статей
 3. Убеждается, что вторая осталась
 4. Переходит в неё и убеждается, что title совпадает
 */
public class ArticleTests extends CoreTestCase {

    private static String searchText;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    @Test
    public void testTitlePresent() {
        searchText = "Java";
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForSearchResultAndClickOneOfThem();
        articlePageObject.assertArticleTitlePresentWithNULLTimeOut();
    }
}
