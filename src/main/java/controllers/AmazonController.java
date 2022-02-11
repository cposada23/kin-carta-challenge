package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageobjects.AmazonHomePage;

public class AmazonController {
    private static final Logger LOGGER = LogManager.getLogger(AmazonController.class);
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;

    public AmazonController(WebDriver driver) {
        this.driver = driver;
        amazonHomePage = new AmazonHomePage(driver);
    }

    public boolean openAmazonAndWaitToLoad(String url) {
        LOGGER.info("Navigating to: " + url);
        driver.get(url);
        LOGGER.info("Validating tha the page was loaded successfully");
        return amazonHomePage.isInputVisible();
    }

}
