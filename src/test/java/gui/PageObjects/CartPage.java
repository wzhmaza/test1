package gui.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

// 1 Filter field with locator class = form-select
// items of the webpage have the locator class = card
// search field with the locator data-test=search-query
// search button with the locator data-test=search-submit
// create a PageObject for the Cart page
public class CartPage {
    @FindBy(className = "form-select")
    private WebElement filterField;

    @FindBy(className = "card")
    private WebElement items;

    @FindBy(xpath = "//input[@data-test='search-query']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@data-test='search-submit']")
    private WebElement searchButton;

    private final WebDriver driver;

    public CartPage(final WebDriver driver) {
        Objects.requireNonNull(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void filterItems(String filter) {
        filterField.sendKeys(filter);
    }

    public void searchItems(String item) {
        searchField.sendKeys(item);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void loadCartPage() {
        driver.get("https://practicesoftwaretesting.com/#/cart");
    }

    public void loadCartPage(String url) {
        driver.get(url);
    }

    public void loadCartPage(String url, int timeout) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public void loadCartPage(String url, int timeout, String title) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        if (!driver.getTitle().equals(title)) {
            throw new IllegalStateException("This is not the cart page");
        }
    }

    public void loadCartPage(String url, int timeout, String title, String element) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        if (!driver.getTitle().equals(title)) {
            throw new IllegalStateException("This is not the cart page");
        }
        if (!driver.findElement(By.id(element)).isDisplayed()) {
            throw new IllegalStateException("This element is not displayed");
        }
    }

    public void loadCartPage(String url, int timeout, String title, String element, String text) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        if (!driver.getTitle().equals(title)) {
            throw new IllegalStateException("This is not the cart page");
        }
    }
}

