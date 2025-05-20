package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final By CART_BUTTON = By.xpath("//*[@data-test='shopping-cart-link']");

    @Step("Открыть корзину")
    public void goToCart() {
        driver.findElement(CART_BUTTON).click();
    }
}
