package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    SoftAssert softAssert;
    LoginPage loginPage;
    ProductsPage productsPage;
    MainPage mainPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
