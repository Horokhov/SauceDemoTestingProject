package org.tests;

import org.openqa.selenium.WebDriver;
import org.pageObjects.*;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class SubmitOrderTest extends  BaseTest {

        @Test(dataProvider = "getData", groups = {"Purchase"})
        public void submitOrder(String username, String password, String productName) throws IOException {

            ProductCatalogue productCatalogue = logInPage.loggination(username, password );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

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
    @DataProvider
    public Object[][] getData() {
           return new Object[][] {{"standard_user", "secret_sauce", "Sauce Labs Bolt T-Shirt" },
                                  {"problem_user", "secret_sauce", "Sauce Labs Onesie"}};
        }

}

