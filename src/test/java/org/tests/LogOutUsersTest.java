package org.tests;

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

public class LogOutUsersTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"LogOut"}, retryAnalyzer = RetryAnalyzer .class)
    public void logOut(HashMap<String, String> input) throws IOException {
        ProductCatalogue productCatalogue = logInPage.loggination(input.get("username"), input.get("password") );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        productCatalogue.openMenu();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        productCatalogue.logOut();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = convertJSONtoMap(System.getProperty("user.dir")+"//src//main//java//org//resources//PurchaseProduct.json");
        return new Object[][] {{data.get(0)},
                {data.get(1)}};
    }
}
