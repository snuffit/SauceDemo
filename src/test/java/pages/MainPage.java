package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final By CART_BUTTON = By.xpath("//*[@data-test='shopping-cart-link']");

    public void goToCart() {
        driver.findElement(CART_BUTTON).click();
    }
}
