package org.tests;

import org.pageObjects.CartPage;
import org.pageObjects.ProductCatalogue;
import org.testComponents.BaseTest;
import org.testComponents.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class ResetAppStateTest extends BaseTest {


    @Test(dataProvider = "getData", groups = {"ResetApp"})
    public void resetApp(HashMap<String, String> input){

        ProductCatalogue productCatalogue = logInPage.loggination(input.get("username"), input.get("password") );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        productCatalogue.getProductByName(input.get("productName"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        productCatalogue.openMenu();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        productCatalogue.resetApp();

        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertFalse(cartPage.verifyCartProducts("productName"));
    }
    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = convertJSONtoMap(System.getProperty("user.dir")+"//src//main//java//org//resources//PurchaseProduct.json");
        return new Object[][] {{data.get(0)},
                {data.get(1)}};
    }
}
