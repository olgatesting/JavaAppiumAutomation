/**
 * Created by User on 21.11.2018.
 * Написать тест, который:
 1)Ищет какое-то слово
 2)Убеждается, что найдено несколько статей
 3)Отменяет поиск
 4)Убеждается, что результат поиска пропал
 */

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

/**
 * Created by User on 12.11.2018.
 */
public class SecondTest  extends CoreTestCase {
    private static String searchText = "lego";
    private SearchPageObject searchPageObject;

    @Test
    public void testSearchResultsDisappear() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.checkIfMoreThenOneSearchResultPresent();
        searchPageObject.waitForSearchLinrAndClear();

        assertTrue(
                searchPageObject.waitForSearchREsultsDisappear());
    }


}