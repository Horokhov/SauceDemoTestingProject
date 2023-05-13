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

public class AboutRedirectionTest extends BaseTest{

    @Test(dataProvider = "getData", groups = {"About"})
    public void aboutRedirection(HashMap<String, String> input) {

        ProductCatalogue productCatalogue = logInPage.loggination(input.get("username"), input.get("password") );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        productCatalogue.openMenu();
        productCatalogue.aboutRedirection();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }


    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = convertJSONtoMap(System.getProperty("user.dir")+"//src//main//java//org//resources//PurchaseProduct.json");
        return new Object[][] {{data.get(0)},
                {data.get(1)}};
    }
}
