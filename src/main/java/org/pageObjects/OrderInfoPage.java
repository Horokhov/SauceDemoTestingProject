package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderInfoPage {

    WebDriver driver;
    public OrderInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    WebElement finishButton;

    public CheckoutCompletePage endOrder() {
        finishButton.click();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        return checkoutCompletePage;
    }
}
