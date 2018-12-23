package lib.ui;

import io.appium.java_client.AppiumDriver;

/**
 * Created by User on 13.12.2018.
 */
public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    private static final String
    MY_LIST_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";

    public void clickMyLists() {
        waitForElementAndClick(MY_LIST_BUTTON, "cannot find MyLists button",5);
    }
}
