package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
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

    @Step("Перейти к странице оформлению заказа")
    public CheckoutPage checkout() {
        log.info("Go to Checkout Page");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    public String getProductPrice(String productName) {
        log.info("Get '{}' product", productName);
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
    }

    public CartPage removeProduct(String productName) {
        log.info("Delete '{}' product", productName);
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, productName))).click();
        return this;
    }
}
