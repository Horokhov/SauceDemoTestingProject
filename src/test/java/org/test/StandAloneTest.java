package org.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.pageObjects.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class StandAloneTest {
    private static String productName = "Sauce Labs Bolt T-Shirt";
    public static void main(String[] args) throws IOException {

        WebDriver driver = new ChromeDriver();

        //LOG IN
        LogInPage logInPage = new LogInPage(driver);

        logInPage.goTo("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        ProductCatalogue productCatalogue = logInPage.loggination("standard_user", "secret_sauce" );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //FILTER

        productCatalogue.filterCatalogue("Name (Z to A)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //VALIDATION OF THE LINKS
        List<Integer> socialLinksCodes = productCatalogue.getSocialLinksCodes("HEAD");

        SoftAssert softAssert = new SoftAssert();

        try {
            for(int codes:socialLinksCodes) {
            softAssert.assertTrue(codes<400, "Social link is broken code: "+codes);
            softAssert.assertAll();
        }
        } catch (AssertionError assertionError) {
            System.out.println(
                    "One of the social links is broken!"
            );
        }
        //CHECKOUT



       productCatalogue.getProductByName(productName);
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

       productCatalogue.addProductToCart(productName);
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

       CartPage cartPage = productCatalogue.goToCart();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

       Boolean match = cartPage.verifyCartProducts(productName);
       Assert.assertTrue(match);

       CheckoutPage checkoutPage = cartPage.checkout();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

       checkoutPage.pastingData("Max", "Horokhov", "79020");

       OrderInfoPage orderInfoPage = checkoutPage.goToOrderConfirmation();

       CheckoutCompletePage checkoutCompletePage = orderInfoPage.endOrder();

       String headerText = checkoutCompletePage.getHeaderText();
       Assert.assertEquals(headerText,"Thank you for your order!");

       String paragraphText = checkoutCompletePage.getParagraphText();
       Assert.assertEquals(paragraphText,"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

       checkoutCompletePage.goToCatalogue();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }
}
