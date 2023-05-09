package org.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.pageObjects.LogInPage;
import org.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class StandAloneTest {
    public static void main(String[] args) throws IOException {

        String productName = "Sauce Labs Bolt T-Shirt";

        WebDriver driver = new ChromeDriver();

        //LOG IN
        LogInPage logInPage = new LogInPage(driver);

        logInPage.goTo("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        logInPage.loggination("standard_user", "secret_sauce" );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //FILTER
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);

        productCatalogue.filterCatalogue("Name (Z to A)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //VALIDATION OF THE LINKS
        List<Integer> socialLinksCodes = productCatalogue.getSocialLinksCodes("HEAD");

        SoftAssert softAssert = new SoftAssert();

        try{for(int codes:socialLinksCodes) {
            softAssert.assertTrue(codes<400, "Social link is broken code: "+codes);
        }} catch (AssertionError assertionError) {
          softAssert.assertAll();
        }
        //CHECKOUT

        List<WebElement> productList = productCatalogue.getProductList();

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
