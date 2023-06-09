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

public class SocialsTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Socials"},retryAnalyzer = RetryAnalyzer.class)
    public void brokenSocialsTest(HashMap<String, String> input) throws IOException {
        ProductCatalogue productCatalogue = logInPage.loggination(input.get("username"), input.get("password") );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        List<Integer> socialLinksCodes = productCatalogue.getSocialLinksCodes("HEAD");

        SoftAssert softAssert = new SoftAssert();

        for(int codes:socialLinksCodes) {
            softAssert.assertTrue(codes < 400, "Social link is broken code: " + codes);
            softAssert.assertAll();
        }}
    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = convertJSONtoMap(System.getProperty("user.dir")+"//src//main//java//org//resources//PurchaseProduct.json");
        return new Object[][] {{data.get(0)},
                {data.get(1)}};
    }




    }


