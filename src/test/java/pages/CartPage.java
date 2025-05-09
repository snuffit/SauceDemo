package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private static final By CONTINUE_SHOPPING_BUTTON = By.xpath("//*[@id='continue-shopping']"),
            CHECKOUT_BUTTON = By.xpath("//*[@id='checkout']");
    private static final String PRODUCT_NAME = "//*[text()='%s']",
            PRODUCT_PRICE = PRODUCT_NAME + "//ancestor::div[@class='cart_item_label']" +
                    "//div[@data-test='inventory-item-price']",
            REMOVE_BUTTON = PRODUCT_NAME + "//ancestor::div[@class='cart_item_label']//button";

    public String getProductName(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_NAME, productName))).getText();
    }

    public void checkout(){
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
    }

    public void removeProduct(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, productName))).click();
    }
}
