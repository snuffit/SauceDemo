package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EndToEndTest extends BaseTest {

    @Test
    public void checkEndToEnd() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        mainPage.goToCart();
        cartPage.checkout();
        checkoutPage.fillFields("Grisha", "Ivanov", "1234");
        checkoutPage.finish();
        assertTrue(checkoutPage.isOrderComplete());
    }
}
