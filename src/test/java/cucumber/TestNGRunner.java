package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "cucumberStepDefinitions", monochrome = true, plugin = {"html:reports/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
