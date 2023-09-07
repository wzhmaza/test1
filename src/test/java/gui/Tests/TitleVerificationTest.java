package gui.Tests;

import gui.PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TitleVerificationTest extends BaseTest {

    @Test
    public void T1_TITLE_verifyTitle_itContainsVersion5() {
        HomePage homePage = new HomePage(driver);
        homePage.loadHomePage();

        String title = homePage.getPageTitle();
        // Verify that the title contains "v5.0"
        Assert.assertTrue(title.contains("v5.0"));
    }
}
