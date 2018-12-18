import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by User on 15.12.2018.
 * В приложении Wikipedia результатом поиска является набор ссылок на статьи, и каждая ссылка содержит как заголовок статьи, так и краткое описание.
 * Например, для запроса “Java” одним из результатов выдачи будет “Java (Programming language)” и описание “Object-oriented programming language”.

 Задача:
 Подобрать локатор, который находит результат поиска одновременно по заголовку и описанию (если заголовок или описание отличается - элемент не находится).
 Добавить соответствующий метод в секцию TEMPLATES METHODS класса SearchPageObject.
 В этот же класс добавить метод waitForElementByTitleAndDescription(String title, String description). Он должен дожидаться результата поиска по двум строкам -
 по заголовку и описанию. Если такой элемент не появляется, тест должен упасть с читаемой и понятной ошибкой.
 Написать тест, который будет делать поиск по любому запросу на ваш выбор (поиск по этому слову должен возвращать как минимум 3 результата).
 Далее тест должен убеждаться, что первых три результата присутствуют в результате поиска.

 Результатом выполнения задания должен быть дифф к коду, который был написан на четвертом занятий. В этом диффе должны быть вспомогательные методы,
 лежащие в соответствующих классах и код теста, лежащего в соответствующем классе. Тест должен работать (т.е. проходить при верном результате поиска и
 обязательно падать, если результат поиска изменился).
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
