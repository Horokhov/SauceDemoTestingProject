package org.tests;

import org.pageObjects.ProductCatalogue;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"Error"})
    public void logInErrorValidation() {
        ProductCatalogue productCatalogue = logInPage.loggination("invalid", "invalid" );
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",logInPage.getErrorMessage());
    }

    @Test(groups = {"Error"})
    public void noLoginErrorValidation() {
        ProductCatalogue productCatalogue = logInPage.loggination("", "" );
        Assert.assertEquals("Epic sadface: Username is required",logInPage.getErrorMessage());
    }
}
