package cucumberStepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pageObjects.*;
import org.testComponents.BaseTest;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

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

//    @Then("^Unique error (.+) is displayed$")
//    public void logginationError(String message){
//        Assert.assertEquals(message,logInPage.getErrorMessage());
//    }
}
