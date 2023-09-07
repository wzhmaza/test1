package gui.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class LoginPage {
    @FindBy(id = "email")
    private WebElement eMailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(className = "btnSubmit")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@data-test='email-error']/div")
    private WebElement errorMessageEMail;

    @FindBy(xpath = "//div[@data-test='password-error']/div")
    private WebElement errorMessagePassword;

    @FindBy(xpath = "//div[@data-test='login-error']/div")
    private WebElement errorMessageLogin;

    private final WebDriver driver;

    public LoginPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void insertEmailAddress(String eMail) {
        eMailField.sendKeys(eMail);
    }
    public void insertPassword(String password) {
        passwordField.sendKeys(password);
    }

    public String getErrorMessageEMail() {
        return errorMessageEMail.getText();
    }

    public String getErrorMessagePassword() {
        return errorMessagePassword.getText();
    }

    public String getErrorMessageLogin() {
        return errorMessageLogin.getText();
    }

    public void clickLogin() {
        submitButton.click();
    }

    public void loadLoginPage() {
        driver.get("https://practicesoftwaretesting.com/#/auth/login");
    }

}
