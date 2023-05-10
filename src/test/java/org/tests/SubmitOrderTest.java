package org.tests;

import org.openqa.selenium.WebDriver;
import org.pageObjects.*;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class SubmitOrderTest extends  BaseTest {
    private static String productName = "Sauce Labs Bolt T-Shirt";


        @Test
        public void submitOrder() throws IOException {



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
    }}

