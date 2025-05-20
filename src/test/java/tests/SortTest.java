package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SortTest extends BaseTest {

    @DataProvider(name = "Проверка сортировки")
    public Object[][] sortData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "Name (A to Z)"},
                {"standard_user", "secret_sauce", "Name (Z to A)"},
                {"standard_user", "secret_sauce", "Price (low to high)"},
                {"standard_user", "secret_sauce", "Price (high to low)"},
        };
    }

    @Test(testName = "Проверка сортировки", dataProvider = "Проверка сортировки", groups = {"regression"})
    @Epic("Сортировка")
    @Feature("Страница логина")
    @Story("Позитивный логин")
    public void checkSort(String user, String password, String typeOfSort) {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.sort(typeOfSort);
        assertTrue(productsPage.isSort(typeOfSort), "Сортировка не работает");
    }
}
