import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        driver.findElement(By.tagName("select"));
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        driver.findElement(By.linkText("About"));
        driver.findElement(By.partialLinkText("State"));
        driver.findElement(By.xpath("//img[@alt='Sauce Labs Bike Light']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        driver.findElement(By.xpath("//img[contains(@data-test,'bike-light-img')]"));
        driver.findElement(By.xpath("//div[contains(text(),'laptop')]"));
        driver.findElement(By.xpath("//div[text()='9.99']/ancestor::div"));
        driver.findElement(By.xpath("//div[@class='header_label']/descendant::*"));
        driver.findElement(By.xpath("//img[@alt='Sauce Labs Bike Light']/following::a"));
        driver.findElement(By.xpath("//div[@class='header_label']/parent::*"));
        driver.findElement(By.xpath("//div[@class='header_label']/.."));
        driver.findElement(By.xpath("//img[@alt='Sauce Labs Bike Light']/preceding::a"));
        driver.findElement(By.xpath(
                "//div[contains(@class,'inventory') and text()='Sauce Labs Backpack']"));
        driver.findElement(By.cssSelector(".page_wrapper"));
        driver.findElement(By.cssSelector(".btn_primary.btn_small"));
        driver.findElement(By.cssSelector(".page_wrapper .header_container"));
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
        driver.findElement(By.cssSelector("select"));
        driver.findElement(By.cssSelector("select.product_sort_container"));
        driver.findElement(By.cssSelector("[data-test='title']"));
        driver.findElement(By.cssSelector("[class~='btn']"));
        driver.findElement(By.cssSelector("[name|='add']"));
        driver.findElement(By.cssSelector("[class^='inventory']"));
        driver.findElement(By.cssSelector("[data-test$='link']"));
        driver.findElement(By.cssSelector("[id*='menu']"));
    }
}
