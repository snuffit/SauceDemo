package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка логина с корректными значениями",
            priority = 1, alwaysRun = true, invocationCount = 3, groups = {"smoke"})
    @Epic("Авторизация")
    @Feature("Страница логина")
    @Story("Позитивный логин")
    public void checkLogin() {
        loginPage.open()
                .login(user, password);
        assertEquals(productsPage.getTitle(), "Products", "Вход не выполнен");
    }

    @DataProvider(name = "Негативные тесты для логина")
    public Object[][] loginData() {
        return new Object[][]{
                {user, "", "Epic sadface: Password is required"},
                {"", password, "Epic sadface: Username is required"},
                {"incorrect", password,
                        "Epic sadface: Username and password do not match any user in this service"},
                {user, "incorrect",
                        "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(testName = "Проверка логина с некорректными значениями", dataProvider = "Негативные тесты для логина",
            priority = 2, groups = "regression")
    @Epic("Авторизация")
    @Feature("Страница логина")
    @Story("Негативный логин")
    @Link(name = "Документация", url = "https://www.saucedemo.com")
    @TmsLink("TMS_T10")
    @Issue("TMS_T11")
    public void checkLoginWithInvalidValue(String user, String password, String errorMessage) {
        loginPage.open()
                .login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage, "Ошибки не обнаружено");
    }

    @Test(enabled = false, testName = "Проверка логина с пустым значением в поле пароль",
            priority = 2, groups = {"regression"})
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Ошибки не обнаружено");
    }

    @Test(enabled = false, testName = "Проверка логина с пустым значением в поле логин",
            priority = 2, groups = {"regression"})
    public void checkLoginWithEmptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Ошибки не обнаружено");
    }

    @Test(enabled = false, testName = "Проверка логина с некорректным значением в поле логин",
            priority = 2, groups = {"regression"})
    public void checkLoginWithIncorreсtLogin() {
        loginPage.open();
        loginPage.login("incorrect", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Ошибки не обнаружено");
    }

    @Test(enabled = false, testName = "Проверка логина с некорректным значением в поле пароль",
            priority = 2, groups = {"regression"})
    public void checkLoginWithIncorreсtPassword() {
        loginPage.open();
        loginPage.login("standard_user", "incorrect");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Ошибки не обнаружено");
    }
}
