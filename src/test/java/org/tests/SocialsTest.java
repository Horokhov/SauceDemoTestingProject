package org.tests;

import org.pageObjects.ProductCatalogue;
import org.testComponents.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SocialsTest extends BaseTest {

    @Test
    public void brokenSocialsTest() throws IOException {
        ProductCatalogue productCatalogue = logInPage.loggination("standard_user", "secret_sauce" );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        List<Integer> socialLinksCodes = productCatalogue.getSocialLinksCodes("HEAD");

        SoftAssert softAssert = new SoftAssert();

        for(int codes:socialLinksCodes) {
            softAssert.assertTrue(codes < 400, "Social link is broken code: " + codes);
            softAssert.assertAll();
        }


    }
}

