package org.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pageObjects.LogInPage;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LogInPage logInPage;
    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\max_h\\IdeaProjects\\SauceDemoTestingProject\\src\\main\\java\\org\\resources\\GlobalData.properties");

        properties.load(fis);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :properties.getProperty("browser");
       // String browserName = properties.getProperty("browser");

       if(browserName.equalsIgnoreCase("chrome")) {

           driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {

           driver = new FirefoxDriver();

       } else if (browserName.equalsIgnoreCase("edge")) {

           driver = new EdgeDriver();

       }
       driver.manage().window().maximize();
       return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LogInPage launchApplication() throws IOException {

        driver = initializeDriver();

        logInPage = new LogInPage(driver);

        logInPage.goTo();

        return logInPage;
    }

    @AfterMethod(alwaysRun = true)
    public void shutDown(){
        driver.close();
    }

    public List<HashMap<String, String>> convertJSONtoMap(String filepath) throws IOException {

        File json = new File(filepath);

        String jsonData = FileUtils.readFileToString(json, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {

        });
        return data;

    }

    public String getScreenShot(String testCase, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot =  (TakesScreenshot) driver;

        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File file = new File(System.getProperty("user.dir") + "//reports//" + testCase + ".png");

        FileUtils.copyFile(source,file);

        return System.getProperty("user.dir") + "//reports//" + testCase + ".png";
    }
}
