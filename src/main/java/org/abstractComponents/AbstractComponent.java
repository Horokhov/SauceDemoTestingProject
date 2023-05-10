package org.abstractComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pageObjects.CartPage;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "shopping_cart_link")
    WebElement cartButton;

    public CartPage goToCart() {
        cartButton.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

}
