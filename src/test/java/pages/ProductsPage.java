package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private static final By TITLE = By.xpath("//*[@data-test='title']"),
            PRODUCT_SORT = By.xpath("//*[@data-test='product-sort-container']"),
            UNIVERSAL_PRODUCT_NAME = By.xpath("//*[@data-test='inventory-item-name']"),
            UNIVERSAL_PRODUCT_PRICE = By.xpath("//*[@data-test='inventory-item-price']");
    private static final String PRODUCT_NAME = "//*[text()='%s']",
            PRODUCT_PRICE = PRODUCT_NAME + "//ancestor::div[@data-test='inventory-item-description']" +
                    "//div[@data-test='inventory-item-price']",
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

    private Select productSort() {
        return new Select(driver.findElement(PRODUCT_SORT));
    }

    private List getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_NAME)) {
            names.add(el.getText());
        }
        return names;
    }

    private List getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_PRICE)) {
            prices.add(Double.parseDouble(el.getText().replaceAll("\\$", "")));
        }
        return prices;
    }

    public void sortNameAZ() {
        productSort().selectByValue("az");
    }

    public boolean isSortNameAZ() {
        List<String> sortedNames = getAllProductNames();
        Collections.sort(sortedNames);
        if (sortedNames.equals(getAllProductNames())) {
            return true;
        } else {
            return false;
        }
    }

    public void sortNameZA() {
        productSort().selectByValue("za");
    }

    public boolean isSortNameZA() {
        List<String> sortedNames = getAllProductNames();
        Collections.sort(sortedNames, Collections.reverseOrder());
        if (sortedNames.equals(getAllProductNames())) {
            return true;
        } else {
            return false;
        }
    }

    public void sortPriceLowToHigh() {
        productSort().selectByValue("lohi");
    }

    public boolean isSortPriceLowToHigh() {
        List<Double> sortedPrices = getAllProductPrices();
        Collections.sort(sortedPrices);
        if (sortedPrices.equals(getAllProductPrices())) {
            return true;
        } else {
            return false;
        }
    }

    public void sortPriceHighToLow() {
        productSort().selectByValue("hilo");
    }

    public boolean isSortPriceHighToLow() {
        List<Double> sortedPrices = getAllProductPrices();
        Collections.sort(sortedPrices, Collections.reverseOrder());
        if (sortedPrices.equals(getAllProductPrices())) {
            return true;
        } else {
            return false;
        }
    }
}
