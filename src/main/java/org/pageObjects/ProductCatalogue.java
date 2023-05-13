package org.pageObjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver) {
        super(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="select.product_sort_container")
    WebElement filterDropdown;

    @FindBy(className = "inventory_item")
    List<WebElement> productList;

    @FindBy(xpath = "//a[@rel='noreferrer']")
    List<WebElement> socialLinks;

    @FindBy(xpath = "//div[@class='right_component']//span//span")
    WebElement activeOption;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logOutLink;

    @FindBy(id = "about_sidebar_link")
    WebElement aboutLink;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppLink;

    public void openMenu() {
        menuButton.click();
    }

    public void logOut() {
        logOutLink.click();
    }

    public void aboutRedirection(){aboutLink.click();}

    public void resetApp(){resetAppLink.click();}

    By addToCart = By.tagName("button");

    public String getActiveOption(){
        String activeFilter = activeOption.getAttribute("innerHTML");
        return activeFilter;
    }


    public void filterCatalogue(String visibleText) {
        Select select = new Select(filterDropdown);

        select.selectByVisibleText(visibleText);
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public List<Integer> getSocialLinksCodes(String requestMethod) throws IOException {

        List<Integer> codes = new ArrayList<>();
        for (WebElement link : socialLinks) {

            String url = link.getAttribute("href");
            HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
            connection.setRequestMethod(requestMethod);
            connection.connect();

            int responseCode = connection.getResponseCode();
            codes.add(responseCode);
        }
        return codes;
    }
    public WebElement getProductByName(String productName) {
        WebElement boltsTshirt = getProductList().stream().filter(tshirt ->
                tshirt.findElement(By.className("inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
        return boltsTshirt;
    }

    public void addProductToCart(String productName){
        WebElement product = getProductByName(productName);
        product.findElement(addToCart).click();
    }


}
