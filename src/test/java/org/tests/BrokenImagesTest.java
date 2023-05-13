package org.tests;

import org.pageObjects.ProductCatalogue;
import org.testComponents.BaseTest;
import org.testComponents.RetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BrokenImagesTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Images"}, retryAnalyzer = RetryAnalyzer.class)
    public void brokenSocialsTest(HashMap<String, String> input) throws IOException {
        ProductCatalogue productCatalogue = logInPage.loggination(input.get("username"),input.get("password"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<String> imgStatusCodes = productCatalogue.isImageExists();

        SoftAssert softAssert = new SoftAssert();

        for (String images : imgStatusCodes) {
            softAssert.assertTrue(images.contains("data:image/png;base64"));
            softAssert.assertAll();
        }
    }
    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = convertJSONtoMap(System.getProperty("user.dir")+"//src//main//java//org//resources//PurchaseProduct.json");
        return new Object[][] {{data.get(0)},
                {data.get(1)}};
    }
}
