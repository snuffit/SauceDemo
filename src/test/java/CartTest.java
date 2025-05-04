import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String itemTitle = driver.findElement(By.xpath("//div[contains(text(),'Backpack')]")).getText();
        String itemPrice = driver.findElement(By.xpath(
                "//button[@id='add-to-cart-sauce-labs-backpack']/preceding-sibling::div")).getText();
        driver.findElement(By.xpath("//button[contains(@id,'backpack')]")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//div[@class='inventory_item_name']")).getText(), itemTitle);
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//div[@class='inventory_item_price']")).getText(), itemPrice);
        softAssert.assertAll();
        System.out.println(itemPrice + " - " + itemTitle);
    }
}
