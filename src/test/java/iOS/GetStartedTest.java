package iOS;

import lib.IOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

/**
 * Created by User on 19.12.2018.
 */
public class GetStartedTest extends IOSTestCase {
    private WelcomePageObject welcomePageObject;
    @Test
    public void testPassThroughWelcome() {
        welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWaysToExploreText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();

    }

}
