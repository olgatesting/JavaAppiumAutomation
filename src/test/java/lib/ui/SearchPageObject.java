package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
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
           SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
           SEARCH_INPUT ="org.wikipedia:id/search_src_text",
           SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
           ALL_SEARCH_RESULTS =
                   "//*[contains(@resource-id,'org.wikipedia:id/search_results_list')]/*[contains(@resource-id,'org.wikipedia:id/page_list_item_container')]",
           SPEСIFIC_SEARCH_RESULT_BY_SUBSTRING_TPL=
                   "//*[contains(@resource-id,'org.wikipedia:id/page_list_item_container')]//*[contains(@text,'{SUBSTRING}')]",
           SEARCH_RESULT_TITLES= "//*[contains(@resource-id,'org.wikipedia:id/page_list_item_title')]";

   /*TEMPLTES METHODS*/
   private static String getResultSearchElement(String substring) {
       return SPEСIFIC_SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
   }
    /*TEMPLTES METHODS*/

   public void initSearchInput() {
       this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "cannot find and click search init element ", 5);
       this.waitForElementPresents(By.id(SEARCH_INPUT), "cannot find search input after clicking init search element",5);
   }
   public void typeSearchLine(String searchText) {
        waitForElementAndEnterText(By.id(SEARCH_INPUT), "cannot find and type into search input", 5, searchText);
    }
   public List<WebElement> waitForSearchResultsList() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage("search results not present");
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ALL_SEARCH_RESULTS)));
        if (list.size()==0) {
            fail("Null results found on the request");
        }
        System.out.println("There are "+list.size() + " results on the page");
        return list;
    }
   public void waitForSpecificSearchResult(String substring) {
       String search_result_xPath = getResultSearchElement(substring);
       waitForElementPresents(By.xpath(search_result_xPath), "cannot find specific search result with substring " +substring ,10);
   }
   public void waitForCancelButtonToAppear() {
       waitForElementPresents(By.id(SEARCH_CANCEL_BUTTON), "cannot find search cancel button", 5);
   }
   public void waitForCancelButtonToDisappear() {
        waitForElementDisappear(By.id(SEARCH_CANCEL_BUTTON), "search cancel button is still present", 5);
    }
   public void clickCancelSearchButton() {
       waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "cannot find and click search cancel button", 5);
   }
   public void clickByArticleWithSubstring(String substring) {
       String search_result_xPath = getResultSearchElement(substring);
       waitForElementAndClick(By.xpath(search_result_xPath), "cannot find and click specific search result with substring " +substring ,10);
   }
   public void waitForSearchResultAndClickOneOfThem(){
      List<WebElement> list =  waitForSearchResultsList();
      int size = list.size();
      int randomI= (int)(Math.random()*size);
      list.get(randomI).click();
    }
    public void waitForSearchResultAndClickOneOfThem(int k){
        List<WebElement> list =  waitForSearchResultsList();
        int size = list.size();
        if (k>=size)throw new IndexOutOfBoundsException("parameter for waitForSearchResultAndClickOneOfThem method more then search result list size");
        list.get(k).click();
    }
    public void checkIfMoreThenOneSearchResultPresent() {
        List<WebElement> list = waitForSearchResultsList();
        assertTrue("Search results are not present or only one", list.size()>1);
    }
    public void assertTextSearchPresentsInTheSearchLine (String textSearch) {
        WebElement element_to_enter_search_line = driver.findElementById(SEARCH_INPUT);
        String textInTheSearchLine = element_to_enter_search_line.getAttribute("text");
        boolean contains = textInTheSearchLine.contains(textSearch);
        assertTrue("Required text does not present in the Search line", contains);
    }
    public void findSearchResultsAndClickOneOfThem(String searchText, int i) {
        this.initSearchInput();
        this.typeSearchLine(searchText);
        this.waitForSearchResultAndClickOneOfThem(i);
    }
    public void waitForSearchLinrAndClear() {
        waitForElementAndClear(By.id(SEARCH_INPUT));
    }
    public boolean waitForSearchREsultsDisappear() {
        List<WebElement> results = driver.findElements(By.xpath(ALL_SEARCH_RESULTS));
        return waitForElementsDisappear(results, "search results are nor disappear", 5);
    }
    public List<WebElement> waitForSearchResultsTitles() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage("search results not present");
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(SEARCH_RESULT_TITLES)));
        if (list.size()==0) {
            fail("Null results found on the request");
        }
        System.out.println("There are "+list.size() + " results on the page");
        return list;
    }
    }

