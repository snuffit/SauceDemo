package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.AllureUtils;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By USER_NAME_FIELD = By.xpath("//*[@id='user-name']"),
            PASSWORD_FIELD = By.xpath("//*[@id='password']"),
            LOGIN_BUTTON = By.xpath("//*[@id='login-button']"),
            ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    @Step("Открытие страницы Login Page")
    public void open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    @Step("Вход в систему с именем пользователя: {user} и паролем {password}")
    public void login(String user, String password) {
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
