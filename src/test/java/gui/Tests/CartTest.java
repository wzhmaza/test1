package gui.Tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class CartTest extends BaseTest {
    @Test
    @Description("Some detailed test description")
    public void T1_CART_addItemToCart_ItemIsInTheCart() throws InterruptedException {
        Reporter.log("Got to the WebPage");
        driver.get("https://practicesoftwaretesting.com/#/");
        sleep(500);
        driver.findElement(By.xpath("//img[contains(@class, 'card-img-top') and contains(@src, 'assets/img/products/pliers01.jpeg')]")).click();
        sleep(500);

        driver.findElement(By.xpath("//button[@data-test='add-to-cart' and @id='btn-add-to-cart']")).click();

        driver.get("https://practicesoftwaretesting.com/#/checkout");
        Reporter.log("Verify Item in Box");
        WebElement productTitleElement = driver.findElement(By.xpath("//span[@class='product-title1']"));

        Assert.assertTrue(productTitleElement.isDisplayed(), "Product Item is displayed in the Cart");
    }
}
