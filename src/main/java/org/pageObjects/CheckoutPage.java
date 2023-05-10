package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueButton;

    public void pastingData(String name, String surname, String zip) {
        firstName.sendKeys(name);

        lastName.sendKeys(surname);

        postalCode.sendKeys(zip);
    }

    public void goToOrderConfirmation() {
        continueButton.click();
    }
}
