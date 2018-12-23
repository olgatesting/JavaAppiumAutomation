package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by User on 13.12.2018.
 */
public class ArticlePageObject extends MainPageObject {
    private final static String
            TITLE = "ID:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            ARTICLE_TITLE = "ID:org.wikipedia:id/view_page_title_text",
            MORE_OPTIONS_BUTTON = "xpath://*[@content-desc='More options']",
            ADD_TO_READING_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            GOT_IT_BUTTON = "ID:org.wikipedia:id/onboarding_button",
            TEXT_INPUT_FIELD = "ID:org.wikipedia:id/text_input",
            OK_BUTTON_FOR_ADD_TO_REDING_BOOK_BLOCK = "xpath://android.widget.Button[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            READING_BOOK_NAME_X_PATH_TPL= "xpath://*[@text='{SUBSTRING}']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLTES METHODS*/
    private static String getReadingBookNameXPath(String substring) {
        return READING_BOOK_NAME_X_PATH_TPL.replace("{SUBSTRING}",substring);
    }
    /*TEMPLTES METHODS*/

    public WebElement waitForTitleElement() {
        return this.waitForElementPresents(TITLE, "cannot find article title on page", 15);
    }
    public String getArticleTitle() {
        WebElement element = this.waitForTitleElement();
        return element.getAttribute("text");
    }
    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "cannot find the end of article", 20);
    }
    public void assertArticleTitlePresentWithNULLTimeOut() {
        this.waitForElementPresents(ARTICLE_TITLE, "Article Title not found", 0);
    }
    public void assertArticleTitlePresent(long timeOutInSec) {
        this.waitForElementPresents(ARTICLE_TITLE, "Article Title not found", timeOutInSec);
    }
    public void addArticleToReadingListFirstTime(String readingBookName) {
        this.waitForElementAndClick(MORE_OPTIONS_BUTTON, "More opptions button not found", 5);
        this.waitForElementAndClick(ADD_TO_READING_LIST_BUTTON, "Add to reading list button not found", 5);
        this.waitForElementAndClick(GOT_IT_BUTTON);
        this.waitForElementAndClear(TEXT_INPUT_FIELD);
        this.waitForElementAndEnterText(TEXT_INPUT_FIELD, readingBookName);
        this.waitForElementAndClick(OK_BUTTON_FOR_ADD_TO_REDING_BOOK_BLOCK, "OK button not found", 5);
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "X button not found", 5);
    }
    public void addArticleToReadingList(String readingBookName) {
        this.waitForElementAndClick(MORE_OPTIONS_BUTTON, "More opptions button not found", 5);
        this.waitForElementAndClick(ADD_TO_READING_LIST_BUTTON, "Add to reading list button not found", 5);
        String bookListName =getReadingBookNameXPath(readingBookName);
        this.waitForElementAndClick(bookListName, "book List not found", 5);
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "X button not found", 5);
    }
    public String saveArticleTitle() {
        return this.waitForElementAndGetAttribute(ARTICLE_TITLE, "text",
                "cannot find article titile and get attribute ", 5);
    }
}
