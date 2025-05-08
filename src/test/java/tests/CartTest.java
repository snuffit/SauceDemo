package tests;

import org.testng.annotations.Test;
import pages.LoginPage;


public class CartTest extends BaseTest {

    @Test
    public void cartTest(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String productName = "Sauce Labs Backpack";
        productsPage.addToCart(productName);
        mainPage.goToCart();
    }
}
