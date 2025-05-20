package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {

    @Test(testName = "Проверка добавления товара в корзину",
            priority = 1, invocationCount = 3, groups = {"smoke"})
    @Epic("Главная страница")
    @Feature("Добавления товара в корзину")
    @Story("Отображение товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Стас")
    @Description("Проверка добавления товара в корзину")
    @Flaky
    @Link(name = "Документация", url = "https://www.saucedemo.com")
    @TmsLink("TMS_T10")
    @Issue("TMS_T11")
    public void checkAddToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String productName = "Sauce Labs Backpack";
        productsPage.addToCart(productName);
        String name = productsPage.getProductName(productName);
        String price = productsPage.getProductPrice(productName);
        mainPage.goToCart();
        softAssert.assertEquals(name, cartPage.getProductName(productName));
        softAssert.assertEquals(price, cartPage.getProductPrice(productName));
        softAssert.assertAll();
    }

    @Test(enabled = false, testName = "Проверка добавления 2 товаров в корзину",
            priority = 2, groups = {"regression"})
    @Epic("Главная страница")
    @Feature("Добавления двух товаров в корзину")
    @Story("Отображение товаров в корзине")
    public void checkAddTwoProductsToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String[] productNames = new String[]{"Sauce Labs Fleece Jacket", "Sauce Labs Bike Light"};
        String[] names = new String[productNames.length];
        String[] prices = new String[productNames.length];
        for (int i = 0; i < productNames.length; i++) {
            names[i] = productsPage.getProductName(productNames[i]);
            prices[i] = productsPage.getProductPrice(productNames[i]);
            productsPage.addToCart(productNames[i]);
        }
        mainPage.goToCart();
        for (int i = 0; i < productNames.length; i++) {
            softAssert.assertEquals(names[i], cartPage.getProductName(productNames[i]));
            softAssert.assertEquals(prices[i], cartPage.getProductPrice(productNames[i]));
        }
        softAssert.assertAll();
    }

    @Test(testName = "Проверка добавления 6 товаров в корзину", priority = 2, groups = {"regression"})
    @Epic("Главная страница")
    @Feature("Добавления 6 товаров в корзину")
    @Story("Отображение товаров в корзину")
    public void checkAddSixProductsToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String[] productNames = new String[]{
                "Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};
        String[] names = new String[productNames.length];
        String[] prices = new String[productNames.length];
        for (int i = 0; i < productNames.length; i++) {
            names[i] = productsPage.getProductName(productNames[i]);
            prices[i] = productsPage.getProductPrice(productNames[i]);
            productsPage.addToCart(productNames[i]);
        }
        mainPage.goToCart();
        for (int i = 0; i < productNames.length; i++) {
            softAssert.assertEquals(names[i], cartPage.getProductName(productNames[i]));
            softAssert.assertEquals(prices[i], cartPage.getProductPrice(productNames[i]));
        }
        softAssert.assertAll();
    }
}
