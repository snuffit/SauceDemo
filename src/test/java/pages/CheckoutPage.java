package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    @Step("Заполнить поля значениями {firstName}, {lastName}, {postalCode} и перейти на следующий этап заказа")
    public void fillFields(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Завершение оформления заказа")
    public void finish() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public double getCorrectTax() {
        return Math.round(getItemTotal() / 12.5 * 100.0) / 100.0;
    }

    public String getOrderMessage() {
        return driver.findElement(COMPLETE_MESSAGE).getText();
    }
}
