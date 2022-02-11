package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AmazonSearchSteps {

    private static final Logger LOGGER = LogManager.getLogger(AmazonSearchSteps.class);
    private WebDriver driver;

    @Before()
    public void setUp() {
        // Browser setup, property read from the test.properties file

        try (
                InputStream input = new FileInputStream("src/test/resources/config/test.properties")
        ) {
            Properties properties = new Properties();
            properties.load(input);

            String browser = properties.getProperty("browser");
            boolean headless = "true".equals(properties.getProperty("headless"));

            LOGGER.info("Executing test in Browser: " + browser);
            LOGGER.info("Headless Mode: " + headless);

            driver = WebDriverFactory.getWebDriver(browser, headless);
        } catch (IOException ex) {
            Assertions.fail("could not load the configuration file"+ ex.getMessage());
        }
    }

    @After()
    public void tearDown() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Given("the user navigates to {string}")
    public void theUserNavigatesTo(String url) {
        LOGGER.info("step 1");
        driver.get(url);
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String search) {
        LOGGER.info("step 1");
    }

    @And("navigates to the second page")
    public void navigatesToTheSecondPage() {
        LOGGER.info("step 1");
    }

    @And("selects the third item")
    public void selectsTheThirdItem() {
        LOGGER.info("step 1");
    }

    @Then("the user is able to add the item to the cart")
    public void theUserIsAbleToAddTheItemToTheCart() {
        LOGGER.info("step 1");
    }
}
