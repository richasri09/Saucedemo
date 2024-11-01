package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SauceDemoSteps {
    private WebDriver driver;

    @Given("I navigate to the Sauce Demo login page")
    public void navigateToLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("I log in with username {string} and password {string}")
    public void logIn(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @And("I select the highest price item")
    public void selectHighestPriceItem() {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        WebElement highestPriceItem = null;
        double highestPrice = 0.0;

        for (WebElement item : items) {
            String priceText = item.findElement(By.className("inventory_item_price")).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            if (price > highestPrice) {
                highestPrice = price;
                highestPriceItem = item;
            }
        }

        assertTrue(highestPriceItem != null);
        highestPriceItem.findElement(By.tagName("button")).click();
    }

    @Then("the item should be in the cart")
    public void verifyItemInCart() {
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        assertTrue(cartBadge.isDisplayed());
        driver.quit();
    }

}