package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductCatalogue {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="select.product_sort_container")
    WebElement filterDropdown;

    @FindBy(className = "inventory_item")
    List<WebElement> productList;

    @FindBy(xpath = "//a[@rel='noreferrer']")
    List<WebElement> socialLinks;

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
}
