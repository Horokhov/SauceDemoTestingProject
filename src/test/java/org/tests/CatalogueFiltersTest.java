package org.tests;

import org.pageObjects.ProductCatalogue;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CatalogueFiltersTest extends BaseTest {

    @Test(groups = {"Filter"})
    public void allFiltersValidation() {
        ProductCatalogue productCatalogue = logInPage.loggination("standard_user", "secret_sauce" );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        productCatalogue.filterCatalogue("Name (Z to A)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),"Name (Z to A)");

        productCatalogue.filterCatalogue("Name (A to Z)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),"Name (A to Z)");

        productCatalogue.filterCatalogue("Price (low to high)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),"Price (low to high)");

        productCatalogue.filterCatalogue("Price (high to low)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),"Price (high to low)");
    }
}
