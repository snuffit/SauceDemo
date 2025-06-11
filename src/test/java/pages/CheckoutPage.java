package pages;

import dto.Checkout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private static final By CANCEL_BUTTON = By.xpath("//*[@data-test='cancel']"),
            CONTINUE_BUTTON = By.xpath("//*[@id='continue']"),
            FIRST_NAME_FIELD = By.xpath("//input[@id='first-name']"),
            LAST_NAME_FIELD = By.xpath("//input[@data-test='lastName']"),
            POSTAL_CODE_FIELD = By.xpath("//input[@data-test='postalCode']"),
            FINISH_BUTTON = By.xpath("//*[@data-test='finish']"),
            ITEM_TOTAL = By.xpath("//*[@data-test='subtotal-label']"),
            TAX = By.xpath("//*[@data-test='tax-label']"),
            TOTAL = By.xpath("//*[@data-test='total-label']"),
            COMPLETE_MESSAGE = By.xpath("//*[@data-test='complete-header']"),
            UNIVERSAL_PRODUCT_PRICE = By.xpath("//*[@data-test='inventory-item-price']");
    private static final String PRODUCT_NAME = "//*[text()='%s']",
            PRODUCT_PRICE = PRODUCT_NAME + "//ancestor::div[@class='cart_item_label']" +
                    "//div[@data-test='inventory-item-price']";

    public double getSumOfProductPrices() {
        log.info("Get sum of product prices");
        double sum = 0;
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_PRICE)) {
            sum += Double.parseDouble(el.getText()
                    .replaceAll("\\$", ""));
        }
        return sum;
    }

    public double getItemTotal() {
        return Double.parseDouble(driver.findElement(ITEM_TOTAL).getText()
                .replaceAll("Item total: \\$", ""));
    }

    public double getTax() {
        return Double.parseDouble(driver.findElement(TAX).getText()
                .replaceAll("Tax: \\$", ""));
    }

    public double getTotal() {
        return Double.parseDouble(driver.findElement(TOTAL).getText()
                .replaceAll("Total: \\$", ""));
    }

    @Step("Заполнить поля значениями и перейти на следующий этап заказа")
    public CheckoutPage fillFields(Checkout checkout) {
        log.info("Fill fields by {}", checkout);
        driver.findElement(FIRST_NAME_FIELD).sendKeys(checkout.getFirstName());
        driver.findElement(LAST_NAME_FIELD).sendKeys(checkout.getLastName());
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(checkout.getPostalCode());
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Завершение оформления заказа")
    public CheckoutPage finish() {
        log.info("Finish checkout");
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }

    public double getCorrectTax() {
        return Math.round(getItemTotal() / 12.5 * 100.0) / 100.0;
    }

    public String getOrderMessage() {
        return driver.findElement(COMPLETE_MESSAGE).getText();
    }
}
