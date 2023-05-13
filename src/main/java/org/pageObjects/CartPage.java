package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item")
    List<WebElement> checkoutProducts;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    By removeFromCart = By.xpath("//button[contains(text(),'Remove')]");

    public Boolean verifyCartProducts(String productName) {
        Boolean match = checkoutProducts.stream().anyMatch(product -> product.findElement(By.className("inventory_item_name")).getText().equals(productName));
        return match;
    }
    public CheckoutPage checkout() {
        checkoutButton.click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }

    public void goFromCartToCatalogue() {
        continueShoppingButton.click();
    }

    public void removeFromCart() {
        driver.findElement(removeFromCart).click();
    }


}
