import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by User on 05.12.2018.
 Написать тест, который:
 1. Сохраняет две статьи в одну папку
 2. Удаляет одну из статей
 3. Убеждается, что вторая осталась
 4. Переходит в неё и убеждается, что title совпадает
 */
public class FourthTest extends TestBase {
    private String articlesTitlesID = "org.wikipedia:id/view_page_title_text";
    private static String searchText = "MayDay";
    private static String readingBookName = "My first list";
    @Test
    public void twoArticlesSaving() {
        String firstArticleTitle = searchForArticleAndSaveTitle(0);
        addArticleToReadingListFirstTime();
        String secondArticleTitle =  searchForArticleAndSaveTitle(2);
        addOneMoreArticleToTheReadingList();

        deleteArticleFromReadingList(secondArticleTitle);
        waitForElementAndClick(By.xpath("//*[@text='"+firstArticleTitle+"']"),"first article not present", 5);
        String articleTitle = waitForElementPresents(By.id(articlesTitlesID), "article title not found", 5).getAttribute("text");
        Assert.assertTrue("titles not equal",articleTitle.equals(firstArticleTitle));

    }
    protected void waitForElementsAndClickOnTheItem(List<WebElement> list,int i, long timeOutInSec){
        if (i>=list.size()) {
            throw new IndexOutOfBoundsException("in the method waitForElementsAndClickOnTheItem insert i more then List od WebElement size");
            //  Assert.fail("in the method waitForElementsAndClickOnTheItem insert i more then List od WebElement size");
        }
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.withMessage(list.get(i).toString() + " is not clickable or not present");
        wait.until(ExpectedConditions.elementToBeClickable(list.get(i)));
        list.get(i).click();
    }
    protected String searchForArticleAndSaveTitle(int i) {
        waitForWikiSearchAndClick();
        waitForSearchLineAndEnterText(searchText);
        List<WebElement> searchResults = waitForSearchResultsList();
        waitForElementsAndClickOnTheItem(searchResults,i, 5);
        String articleTitle = waitForElementPresents(By.id(articlesTitlesID), "article title not found", 5).getAttribute("text");
        System.out.println("article Title is "+ articleTitle);
        return articleTitle;
    }
    protected void addArticleToReadingListFirstTime() {
        waitForElementAndClick(By.xpath("//*[@content-desc='More options']"), "More opptions button not found",5);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),"Add to reading list button not found", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"));
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"));
        waitForElementAndEnterText(By.id("org.wikipedia:id/text_input"), readingBookName);
        waitForElementAndClick(By.xpath("//android.widget.Button[@text='OK']"), "OK button not found", 5);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"), "X button not found", 5);
    }
    protected void addOneMoreArticleToTheReadingList() {
        waitForElementAndClick(By.xpath("//*[@content-desc='More options']"), "More opptions button not found",5);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Add to reading list button not found", 5);
        String bookListName= "//*[@text='"+readingBookName+"']";
        waitForElementAndClick(By.xpath(bookListName), "book List not found" , 5);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"), "X button not found", 5);
    }
    protected void deleteArticleFromReadingList (String articleName) {
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]"));
        waitForElementAndClick(By.xpath("//*[@text='"+readingBookName+"']"));
        swipeElementToLeft(By.xpath("//*[@text='"+articleName+"']"), "article should be deleted not found");
        waitForElementDisappear(By.xpath("//*[@text='"+articleName+"']"),"second article don't disappear", 5);
    }
}
