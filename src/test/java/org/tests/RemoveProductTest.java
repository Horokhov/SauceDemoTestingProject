package org.tests;

import org.pageObjects.CartPage;
import org.pageObjects.ProductCatalogue;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class RemoveProductTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"RemoveProduct"})
    public void removeProductFromCart(HashMap<String, String> input) {
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

        cartPage.removeFromCart();

        Boolean matchFalse = cartPage.verifyCartProducts(input.get("productName"));

        Assert.assertFalse(matchFalse);
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = convertJSONtoMap(System.getProperty("user.dir")+"//src//main//java//org//resources//PurchaseProduct.json");
        return new Object[][] {{data.get(0)},
                {data.get(1)}};
    }

}
