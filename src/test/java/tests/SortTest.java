package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SortTest extends BaseTest {

    @Test
    public void checkSortNameAZ() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sort("Name (A to Z)");
        assertTrue(productsPage.isSortNameAZ(), "Сортировка не работает");
    }

    @Test
    public void checkSortNameZA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sort("Name (Z to A)");
        assertTrue(productsPage.isSortNameZA(), "Сортировка не работает");
    }

    @Test
    public void checkSortPriceLowtoHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sort("Price (low to high)");
        assertTrue(productsPage.isSortPriceLowToHigh(), "Сортировка не работает");
    }

    @Test
    public void checkSortPriceHighToLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sort("Price (high to low)");
        assertTrue(productsPage.isSortPriceHighToLow(), "Сортировка не работает");
    }
}
