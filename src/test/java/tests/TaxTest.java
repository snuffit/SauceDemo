package tests;

import org.testng.annotations.Test;

public class TaxTest extends BaseTest {

    @Test
    public void checkTax() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        mainPage.goToCart();
        cartPage.checkout();
        checkoutPage.fillFields("Grisha", "Ivanov", "1234");
        softAssert.assertTrue(checkoutPage.isItemTotalEqualSum());
        softAssert.assertTrue(checkoutPage.isTaxCorrect());
        softAssert.assertTrue(checkoutPage.isTotalCorrect());
        softAssert.assertAll();
    }

    @Test
    public void checkTaxOfTwoProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        mainPage.goToCart();
        cartPage.checkout();
        checkoutPage.fillFields("Grisha", "Ivanov", "1234");
        softAssert.assertTrue(checkoutPage.isItemTotalEqualSum());
        softAssert.assertTrue(checkoutPage.isTaxCorrect());
        softAssert.assertTrue(checkoutPage.isTotalCorrect());
        softAssert.assertAll();
    }
}
