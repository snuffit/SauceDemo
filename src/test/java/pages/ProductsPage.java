package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private static final By TITLE = By.xpath("//*[@data-test='title']");
    private static final String PRODUCT_NAME = "//*[text()='%s']",
            PRODUCT_PRICE = PRODUCT_NAME
                    + "//ancestor::div[@data-test='inventory-item-description']//div[@data-test='inventory-item-price']",
            ADD_TO_CART_BUTTON = PRODUCT_NAME + "//ancestor::div[@data-test='inventory-item-description']//button";

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getProductName(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_NAME, productName))).getText();
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
    }

    public void addToCart(String productName) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON, productName))).click();
    }
}
