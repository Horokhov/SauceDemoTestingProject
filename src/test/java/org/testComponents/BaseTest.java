package org.testComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pageObjects.LogInPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\max_h\\IdeaProjects\\SauceDemoTestingProject\\src\\main\\java\\org\\resources\\GlobalData.properties");

        properties.load(fis);
        String browserName = properties.getProperty("browser");

       if(browserName.equalsIgnoreCase("chrome")) {

           driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {

           driver = new FirefoxDriver();

       } else if (browserName.equalsIgnoreCase("edge")) {

           driver = new EdgeDriver();

       }

       return driver;
    }

    public LogInPage launchApplication(String url) throws IOException {

        driver = initializeDriver();

        LogInPage logInPage = new LogInPage(driver);

        logInPage.goTo(url);

        return logInPage;
    }
}
