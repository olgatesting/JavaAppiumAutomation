package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by User on 12.12.2018.
 */
public class SearchPageObject extends MainPageObject {
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "ID:org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "ID:org.wikipedia:id/search_close_btn",

//    SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
//            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            ALL_SEARCH_RESULTS =
                    "xpath://*[contains(@resource-id,'org.wikipedia:id/search_results_list')]/*[contains(@resource-id,'org.wikipedia:id/page_list_item_container')]",
            SEARCH_RESULT_TITLES = "xpath://*[contains(@resource-id,'org.wikipedia:id/page_list_item_title')]",

            SPEСIFIC_SEARCH_RESULT_BY_SUBSTRING_TPL =
                    "//*[contains(@resource-id,'org.wikipedia:id/page_list_item_container')]//*[contains(@text,'{SUBSTRING}')]",
            SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[./*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}'] and ./*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']]";

    /*TEMPLTES METHODS*/
    private static String getResultSearchElement(String substring) {
        return SPEСIFIC_SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESC_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /*TEMPLTES METHODS*/

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "cannot find and click search init element ", 5);
        this.waitForElementPresents(SEARCH_INPUT, "cannot find search input after clicking init search element", 5);
    }

    public void typeSearchLine(String searchText) {
        waitForElementAndEnterText(SEARCH_INPUT, "cannot find and type into search input", 5, searchText);
    }

    public List<WebElement> waitForSearchResultsList() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage("search results not present");
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocatorByString(ALL_SEARCH_RESULTS)));
        if (list.size() == 0) {
            fail("Null results found on the request");
        }
        System.out.println("There are " + list.size() + " results on the page");
        return list;
    }

    public void waitForCancelButtonToAppear() {
        waitForElementPresents(SEARCH_CANCEL_BUTTON, "cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        waitForElementDisappear(SEARCH_CANCEL_BUTTON, "search cancel button is still present", 5);
    }

    public void clickCancelSearchButton() {
        waitForElementAndClick(SEARCH_CANCEL_BUTTON, "cannot find and click search cancel button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result = getResultSearchElement(substring);
        waitForElementAndClick(search_result, "cannot find and click specific search result with substring " + substring, 10);
    }

    public void waitForSearchResultAndClickOneOfThem() {
        List<WebElement> list = waitForSearchResultsList();
        int size = list.size();
        int randomI = (int) (Math.random() * size);
        list.get(randomI).click();
    }

    public void waitForSearchResultAndClickOneOfThem(int k) {
        List<WebElement> list = waitForSearchResultsList();
        int size = list.size();
        if (k >= size)
            throw new IndexOutOfBoundsException("parameter for waitForSearchResultAndClickOneOfThem method more then search result list size");
        list.get(k).click();
    }

    public void assertMoreThenKElementsPresent(int k) {
        List<WebElement> list = waitForSearchResultsList();
        assertTrue("Search results are not present or less then expected", list.size() > k);
    }

    public void assertTextSearchPresentsInTheSearchLine(String textSearch) {
        String textInTheSearchLine =  this.waitForElementPresents(SEARCH_INPUT, "cannot find search input line", 5).getAttribute("text");
        boolean contains = textInTheSearchLine.contains(textSearch);
        assertTrue("Required text does not present in the Search line", contains);
    }

    public void findSearchResultsAndClickOneOfThem(String searchText, int i) {
        this.initSearchInput();
        this.typeSearchLine(searchText);
        this.waitForSearchResultAndClickOneOfThem(i);
    }

    public void waitForSearchLineAndClear() {
        waitForElementAndClear(SEARCH_INPUT);
    }

    public boolean waitForSearchResultsDisappear() {
        List<WebElement> results = driver.findElements(getLocatorByString(ALL_SEARCH_RESULTS));
        return waitForElementsDisappear(results, "search results are nor disappear", 5);
    }

    public List<WebElement> waitForSearchResultsTitles() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage("search results not present");
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocatorByString(SEARCH_RESULT_TITLES)));
        if (list.size() == 0) {
            fail("Null results found on the request");
        }
        System.out.println("There are " + list.size() + " results on the page");
        return list;
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_BY_TITLE_AND_DESCRIPTION_xPath = getSearchResultByTitleAndDescription(title, description);
        waitForElementPresents(search_result_BY_TITLE_AND_DESCRIPTION_xPath,
                "cannot find search result with title " + title + " and description " + description
                , 10);
    }
}

