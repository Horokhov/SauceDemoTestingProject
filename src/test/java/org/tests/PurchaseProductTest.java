package org.tests;

import org.pageObjects.*;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class PurchaseProductTest extends  BaseTest {

        @Test(dataProvider = "getData", groups = {"Purchase"})
        public void submitOrder(HashMap<String, String> input) throws IOException {

            ProductCatalogue productCatalogue = logInPage.loggination(input.get("username"), input.get("password") );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        productCatalogue.getProductByName(input.get("productName"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

       productCatalogue.addProductToCart(input.get("productName"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

       CartPage cartPage = productCatalogue.goToCart();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

       Boolean match = cartPage.verifyCartProducts(input.get("productName"));
       Assert.assertTrue(match);

       CheckoutPage checkoutPage = cartPage.checkout();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

       checkoutPage.pastingData(input.get("name"), input.get("surname"), input.get("zip"));

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

            HashMap<String, String> dataMap = new HashMap<>();

            dataMap.put("username", "standard_user");
            dataMap.put("password", "secret_sauce");
            dataMap.put("productName", "Sauce Labs Bolt T-Shirt");
            dataMap.put("name", "Max");
            dataMap.put("surname", "Horokhov");
            dataMap.put("zip", "79020");


        HashMap<String, String> dataMap1 = new HashMap<>();

        dataMap1.put("username", "problem_user");
        dataMap1.put("password", "secret_sauce");
        dataMap1.put("productName", "Sauce Labs Onesie");
        dataMap1.put("name", "Max");
        dataMap1.put("surname", "Horokhov");
        dataMap1.put("zip", "79020");

           return new Object[][] {{dataMap},
                                  {dataMap1}};
        }

}

