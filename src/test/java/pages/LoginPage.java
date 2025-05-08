package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By USER_NAME_FIELD = By.xpath("//*[@id='user-name']"),
            PASSWORD_FIELD = By.xpath("//*[@id='password']"),
            LOGIN_BUTTON = By.xpath("//*[@id='login-button']"),
            ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String user, String password) {
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
