package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private double getSumOfProductPrices() {
        double sum = 0.00;
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_PRICE)) {
            Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
            Matcher matcher = pattern.matcher(el.getText());
            if (matcher.find()) {
                sum += Double.parseDouble(matcher.group(1));
            } else {
                return 0;
            }
        }
        return sum;
    }

    private double getItemTotal() {
        Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
        Matcher matcher = pattern.matcher(driver.findElement(ITEM_TOTAL).getText());
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0;
    }

    private double getTax() {
        Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
        Matcher matcher = pattern.matcher(driver.findElement(TAX).getText());
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0;
    }

    private double getTotal() {
        Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
        Matcher matcher = pattern.matcher(driver.findElement(TOTAL).getText());
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0;
    }

    public void fillFields(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void finish() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public boolean isItemTotalEqualSum() {
        if (getItemTotal() == getSumOfProductPrices()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTaxCorrect() {
        double correctTax = getItemTotal() / 12.5;
        if (getTax() == (Math.round(correctTax * 100.0) / 100.0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTotalCorrect() {
        if (getTax() + getItemTotal() == getTotal()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOrderComplete() {
        if (driver.findElement(COMPLETE_MESSAGE).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
