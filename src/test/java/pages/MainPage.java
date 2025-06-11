package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final By CART_BUTTON = By.xpath("//*[@data-test='shopping-cart-link']");

    @Step("Открыть корзину")
    public CartPage goToCart() {
        log.info("Go to cart");
        driver.findElement(CART_BUTTON).click();
        return new CartPage(driver);
    }
}
