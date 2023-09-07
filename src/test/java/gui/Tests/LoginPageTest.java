package gui.Tests;

import gui.PageObjects.LoginPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;

    @Test
    public void testLoginSuccess() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.insertEmailAddress("user@example.com");
        loginPage.insertPassword("password123");
        loginPage.clickLogin();
        // Assuming the success login takes the user to another page, you can add verification logic here.
        // For example, you can check if a certain element exists on the next page to verify the successful login.
    }

    @Test
    public void testLoginFailure() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.insertEmailAddress("invaliduser@example.com");
        loginPage.insertPassword("wrongpassword");
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessageLogin(), "Invalid email or password");
    }

    @Test
    public void testEmptyPasswordField() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.insertEmailAddress("test@test.at");
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessagePassword(), "Password is required.");
    }

    @Test
    public void testEmptyEmailField() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.insertPassword("test");
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessageEMail(), "E-mail is required!.");
    }

    @Test
    public void testEmptyEmailAndPasswordFields() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessageEMail(), "E-mail is required.");
        Assert.assertEquals(loginPage.getErrorMessagePassword(), "Password is required.");
    }

    @Test
    public void testEmptyEmailAndPasswordFieldsWithTab() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.insertEmailAddress(Keys.TAB.toString());
        loginPage.insertPassword(Keys.TAB.toString());
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessageEMail(), "E-mail is required.");
        Assert.assertEquals(loginPage.getErrorMessagePassword(), "Password is required.");
    }

    @Test
    public void testEmptyEmailAndPasswordFieldsWithTabAndEnter() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.insertEmailAddress(Keys.TAB.toString());
        loginPage.insertPassword(Keys.TAB.toString());
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessageEMail(), "E-mail is required.");
        Assert.assertEquals(loginPage.getErrorMessagePassword(), "Password is required.");
    }

    @Test
    public void testEmptyEmailAndPasswordFieldsWithTabAndEnter2() {
        loginPage = new LoginPage(driver);
        loginPage.loadLoginPage();
        loginPage.insertEmailAddress(Keys.TAB.toString());
        loginPage.insertPassword(Keys.ENTER.toString());

        Assert.assertEquals(loginPage.getErrorMessageEMail(), "E-mail is required.");
        Assert.assertEquals(loginPage.getErrorMessagePassword(), "Password is required.");
    }

}
