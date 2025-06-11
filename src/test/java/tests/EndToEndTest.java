package tests;

import dto.Checkout;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndToEndTest extends BaseTest {

    @Test(testName = "End-to-end тест", groups = {"regression"})
    @Epic("Сквозное тестирование")
    @Owner("Стас")
    public void checkEndToEnd() {
        Checkout checkout = Checkout.builder()
                .lastName("Name")
                .firstName("Bob")
                .postalCode("12234")
                .build();
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack");
        mainPage.goToCart()
                .checkout()
                .fillFields(checkout)
                .finish();
        assertEquals(checkoutPage.getOrderMessage(), "Thank you for your order!",
                "Неправильный текст сообщения");
    }
}
