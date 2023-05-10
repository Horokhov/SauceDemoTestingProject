package org.tests;

import org.pageObjects.ProductCatalogue;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CatalogueFiltersTest extends BaseTest {

    @Test
    public void allFiltersValidation() {
        ProductCatalogue productCatalogue = logInPage.loggination("standard_user", "secret_sauce" );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        productCatalogue.filterCatalogue("Name (Z to A)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        System.out.println(productCatalogue.getActiveOption());
    }
}
