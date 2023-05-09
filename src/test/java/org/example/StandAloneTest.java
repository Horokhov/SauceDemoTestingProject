package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws IOException {

        String productName = "Sauce Labs Bolt T-Shirt";

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //LOG IN

        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //FILTER

        WebElement filterDropdown = driver.findElement(By.cssSelector("select.product_sort_container"));

        Select select = new Select(filterDropdown);

        select.selectByVisibleText("Name (Z to A)");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //VALIDATION OF THE LINKS

        List<WebElement> socialLinks = driver.findElements(By.xpath("//a[@rel='noreferrer']"));

        SoftAssert softAssert = new SoftAssert();

        for(WebElement link:socialLinks) {

            String url = link.getAttribute("href");

            if(url == null || url.isEmpty()){
                Assert.assertFalse(true);
                System.out.println("The link does not exists or broken!");
                continue;
            }

            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
            connection.setRequestMethod("HEAD");
            connection.connect();

            int responseCode = connection.getResponseCode();

            softAssert.assertTrue(responseCode<400,"The link with text " + link.getText() + " is broken with code "+responseCode);
        }

        //CHECKOUT

        List<WebElement> productList = driver.findElements(By.className("inventory_item"));

        WebElement boltsTshirt = productList.stream().filter(tshirt ->
                tshirt.findElement(By.className("inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        boltsTshirt.findElement(By.tagName("button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> checkoutProducts = driver.findElements(By.className("cart_item"));

        Boolean match = checkoutProducts.stream().anyMatch(product -> product.findElement(By.className("inventory_item_name")).getText().equals(productName));
        Assert.assertTrue(match);

        driver.findElement(By.id("checkout")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Max");

        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Horokhov");

        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("79020");

        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();

        String header = driver.findElement(By.className("complete-header")).getText();
        String text = driver.findElement(By.className("complete-text")).getText();
        Assert.assertEquals(header,"Thank you for your order!");
        Assert.assertEquals(text,"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }
}
