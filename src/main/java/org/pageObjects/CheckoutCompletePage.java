package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    WebDriver driver;
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    WebElement header;

    @FindBy(className = "complete-text")
    WebElement text;

    @FindBy(id = "back-to-products")
    WebElement catalogueButton;

    public String getHeaderText() {
        String headerText = header.getText();
        return headerText;
    }

    public String getParagraphText() {
        String paragraphText = text.getText();
        return paragraphText;
    }

    public ProductCatalogue goToCatalogue() {
        catalogueButton.click();

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }
}
