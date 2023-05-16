package cucumberStepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pageObjects.*;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StepsDefiniton extends BaseTest {

    public LogInPage logInPage;
    public ProductCatalogue productCatalogue;
    public OrderInfoPage orderInfoPage;
    public CheckoutCompletePage checkoutCompletePage;
    public CartPage cartPage;
    @Given("Landed on the website")
    public void landingOnWebsite() throws IOException {
        initializeDriver();

        logInPage = new LogInPage(driver);
        logInPage.goTo();
    }

    @Given("^Logged in with (.+) and (.+)$")
    public void loggination(String username, String password){
        logInPage.loggination(username, password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @When("^Product with name (.+) is added to Cart$")
    public void purchase(String productName){
        productCatalogue = new ProductCatalogue(driver);
        productCatalogue.getProductByName(productName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit an order using (.+), (.+) and (.+)$")
    public void checkout(String productName, String name, String surname, String zip){
        cartPage = new CartPage(driver);
        CartPage cartPage = productCatalogue.goToCart();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Boolean match = cartPage.verifyCartProducts(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.checkout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        checkoutPage.pastingData(name, surname, zip);

        checkoutPage.goToOrderConfirmation();

        orderInfoPage = new OrderInfoPage(driver);

        orderInfoPage.endOrder();
    }

    @Then("{string} header and {string} paragraph are displayed")
    public void messageDisplay(String string, String string1) {
        checkoutCompletePage = new CheckoutCompletePage(driver);

        String headerText = checkoutCompletePage.getHeaderText();
        Assert.assertEquals(headerText,string);

        String paragraphText = checkoutCompletePage.getParagraphText();
        Assert.assertEquals(paragraphText,string1);

        checkoutCompletePage.goToCatalogue();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.close();
    }

    @Then("^Unique error (.+) is displayed$")
    public void logginationError(String message){
        Assert.assertEquals(message,logInPage.getErrorMessage());
    }

    @When("Menu is opened")
    public void openMenu(){
        productCatalogue = new ProductCatalogue(driver);
        productCatalogue.openMenu();
    }

    @And("\"About\" field is clicked")
    public void aboutRedirection(){
        productCatalogue = new ProductCatalogue(driver);
        productCatalogue.aboutRedirection();
    }



    @Then("Redirected to the main website saucelabs.com")
    public void aboutValidation(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @Then("Verified that images are displayed on the page in Base64 format")
    public void brokenImagesValidation() throws IOException {
        productCatalogue = new ProductCatalogue(driver);
        List<String> imgStatusCodes = productCatalogue.isImageExists();

        SoftAssert softAssert = new SoftAssert();

        for (String images : imgStatusCodes) {
            softAssert.assertTrue(images.contains("data:image/png;base64"));
            softAssert.assertAll();
        }
    }

    @Then("Switched every filter {string}, {string}, {string}, {string} and verified that it is displayed")
    public void catalogueFiltersValidation(String string1, String string2, String string3, String string4){
        productCatalogue = new ProductCatalogue(driver);
        productCatalogue.filterCatalogue(string1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),string1);

        productCatalogue.filterCatalogue(string2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),string2);

        productCatalogue.filterCatalogue(string3);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),string3);

        productCatalogue.filterCatalogue(string4);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(productCatalogue.getActiveOption(),string4);
    }

    @Then("Log out using \"Log out\" field in menu")
    public void logOut(){
        productCatalogue = new ProductCatalogue(driver);

        productCatalogue.logOut();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Then("^Product with name (.+) is successfully removed from Cart$")
    public void removeProduct(String productName){

        cartPage = new CartPage(driver);
        productCatalogue = new ProductCatalogue(driver);

        productCatalogue.goToCart();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Boolean match = cartPage.verifyCartProducts(productName);
        Assert.assertTrue(match);

        cartPage.removeFromCart();

        Boolean matchFalse = cartPage.verifyCartProducts(productName);

        Assert.assertFalse(matchFalse);
    }

    @Then("^\"Reset App\" field is used and product with (.+) in cart is deleted$")
    public void resetApp(String productName){
        productCatalogue = new ProductCatalogue(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        productCatalogue.resetApp();

        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertFalse(cartPage.verifyCartProducts(productName));
    }

    @Then("Social links are scanned for broken links")
    public void socialLinks() throws IOException {
        productCatalogue = new ProductCatalogue(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        List<Integer> socialLinksCodes = productCatalogue.getSocialLinksCodes("HEAD");

        SoftAssert softAssert = new SoftAssert();

        for(int codes:socialLinksCodes) {
            softAssert.assertTrue(codes < 400, "Social link is broken code: " + codes);
            softAssert.assertAll();
        }}
    }

