package lib.ui;

import io.appium.java_client.AppiumDriver;

/**
 * Created by User on 13.12.2018.
 */
public class MyListsPageObject extends MainPageObject{
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    private static final
    String READING_BOOK_NAME_X_PATH_TPL= "xpath://*[contains(@text,'{SUBSTRING}')]",
            ARTICLE_TITLE_XPATH_TPL= "xpath://*[@text='{SUBSTRING}']";

    /*TEMPLTES METHODS*/
    private static String getArticleTitleXPath(String substring) {
        return ARTICLE_TITLE_XPATH_TPL.replace("{SUBSTRING}",substring);
    }
    private static String getReadingBookNameXPath(String substring) {
        return READING_BOOK_NAME_X_PATH_TPL.replace("{SUBSTRING}",substring);
    }
    /*TEMPLTES METHODS*/

    public void SwipeByArticleAndDelete(String articleName) {
        waitForArticleToAppearByTitle(articleName);
        this.swipeElementToLeft(getArticleTitleXPath(articleName), "article should be swipe to be deleted not found");
        this.waitForElementDisappear(getArticleTitleXPath(articleName), "article should be deleted don't disappear", 5);
    }
    public void openReadingListFolderByName(String readingBookName) {
        waitForElementAndClick(getReadingBookNameXPath(readingBookName));
    }
    public void openArticleInReadingList(String articleTitle) {
        waitForElementAndClick(getArticleTitleXPath(articleTitle), "article not present", 5);
    }
    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresents(getArticleTitleXPath(articleTitle), "cannot find saved article by title",10);
    }

}
