import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(), "Products");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Password is required");
    }

    @Test
    public void checkLoginWithEmptyLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username is required");
    }

    @Test
    public void checkLoginWithIncorretLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("Unstandard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void checkLoginWithIncorretPassword() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("Unsecret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
