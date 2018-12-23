package lib.ui;


import io.appium.java_client.ios.IOSDriver;

/**
 * Created by User on 19.12.2018.
 */
public class WelcomePageObject extends MainPageObject{

    public WelcomePageObject(IOSDriver driver) {
        super(driver);
    }

    private static final String
    STEP_LEARN_MORE_LINK="ID:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT="ID:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANG="ID:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED="ID:Learn more about data collected",
    NEXT_BUTTON="ID:Next",
    GET_STARTED_BUTTON="ID:Get started";


    public void waitForLearnMoreLink() {
        this.waitForElementPresents(STEP_LEARN_MORE_LINK, "can not find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresents(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "can not find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresents(STEP_ADD_OR_EDIT_PREFERRED_LANG, "can not find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresents(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "can not find 'Learn more about data collected' link", 10);
    }


    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BUTTON, "can not find and click 'Next' link", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementPresents(GET_STARTED_BUTTON, "can not find and click 'Get started' link", 10);
    }
}
