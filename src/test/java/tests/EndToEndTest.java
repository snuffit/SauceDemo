package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndToEndTest extends BaseTest {

    @Test(testName = "End-to-end тест", groups = {"regression"})
    public void checkEndToEnd() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        mainPage.goToCart();
        cartPage.checkout();
        checkoutPage.fillFields("Grisha", "Ivanov", "1234");
        checkoutPage.finish();
        assertEquals(checkoutPage.getOrderMessage(), "Thank you for your order!",
                "Неправильный текст сообщения");
    }
}
