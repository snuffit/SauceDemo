package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class TaxTest extends BaseTest {

    @Test(testName = "Проверка подсчета налога на товар", priority = 1, groups = {"smoke"})
    @Epic("Подсчет налога")
    @Feature("Страница заказа")
    @Owner("Стас")
    public void checkTax() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        mainPage.goToCart();
        cartPage.checkout();
        checkoutPage.fillFields("Grisha", "Ivanov", "1234");
        softAssert.assertEquals(checkoutPage.getItemTotal(), checkoutPage.getSumOfProductPrices(),
                "Суммы не совпадают");
        softAssert.assertEquals(checkoutPage.getTax(), checkoutPage.getCorrectTax(),
                "Налог посчитан неверно");
        softAssert.assertEquals((checkoutPage.getTax() + checkoutPage.getItemTotal()), checkoutPage.getTotal(),
                "Итоговая сумма неправильная");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка подсчета налога на два товара", priority = 2, groups = {"regression"})
    @Epic("Подсчет налога")
    @Feature("Страница заказа")
    @Story("Подсчет налога для двух товаров")
    @Owner("Стас")
    public void checkTaxOfTwoProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        mainPage.goToCart();
        cartPage.checkout();
        checkoutPage.fillFields("Grisha", "Ivanov", "1234");
        softAssert.assertEquals(checkoutPage.getItemTotal(), checkoutPage.getSumOfProductPrices(),
                "Суммы не совпадают");
        softAssert.assertEquals(checkoutPage.getTax(), checkoutPage.getCorrectTax(),
                "Налог посчитан неверно");
        softAssert.assertEquals((checkoutPage.getTax() + checkoutPage.getItemTotal()), checkoutPage.getTotal(),
                "Итоговая сумма неправильная");
        softAssert.assertAll();
    }
}
