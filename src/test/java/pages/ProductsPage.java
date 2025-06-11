package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
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
        log.info("Get title");
        return driver.findElement(TITLE).getText();
    }

    public String getProductName(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_NAME, productName))).getText();
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
    }

    @Step("Добавление товара в корзину")
    public ProductsPage addToCart(String productName) {
        log.info("Add to cart '{}' product", productName);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON, productName))).click();
        return this;
    }

    public List getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_NAME)) {
            names.add(el.getText());
        }
        return names;
    }

    public List getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_PRICE)) {
            prices.add(Double.parseDouble(el.getText().replaceAll("\\$", "")));
        }
        return prices;
    }
    @Step("Сортировка {typeOfSort}")
    public ProductsPage sort(String typeOfSort) {
        log.info("Sort by {}", typeOfSort);
        Select select = new Select(driver.findElement(PRODUCT_SORT));
        select.selectByVisibleText(typeOfSort);
        return this;
    }

    public boolean isSort(String typeOfSort) {
        List<String> sortedNames = getAllProductNames();
        List<Double> sortedPrices = getAllProductPrices();
        switch (typeOfSort) {
            case "Name (A to Z)":
                Collections.sort(sortedNames);
                return sortedNames.equals(getAllProductNames());
            case "Name (Z to A)":
                Collections.sort(sortedNames, Collections.reverseOrder());
                return sortedNames.equals(getAllProductNames());
            case "Price (low to high)":
                Collections.sort(sortedPrices);
                return sortedPrices.equals(getAllProductPrices());
            case "Price (high to low)":
                Collections.sort(sortedPrices, Collections.reverseOrder());
                return sortedPrices.equals(getAllProductPrices());
            default:
                return false;
        }
    }
}
