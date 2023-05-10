package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    WebDriver driver;
    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="user-name")
    WebElement elementUsername;

    @FindBy(id="password")
    WebElement elementPassword;

    @FindBy(id="login-button")
    WebElement loginButton;

    public ProductCatalogue loggination(String username, String password) {
        elementUsername.sendKeys(username);

        elementPassword.sendKeys(password);

        loginButton.click();

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo(String url) {
        driver.get(url);

        driver.manage().window().maximize();
    }

}
