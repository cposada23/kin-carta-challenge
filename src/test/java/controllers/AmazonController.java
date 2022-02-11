package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageobjects.AmazonHomePage;
import pageobjects.AmazonSearchResultPage;

public class AmazonController {
    private static final Logger LOGGER = LogManager.getLogger(AmazonController.class);
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    private AmazonSearchResultPage amazonSearchResultPage;

    public AmazonController(WebDriver driver) {
        this.driver = driver;
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResultPage = new AmazonSearchResultPage(driver);
    }

    public void openAmazonAndWaitToLoad(String url) {
        LOGGER.info("Navigating to: " + url);
        driver.get(url);
        LOGGER.info("Validating tha the page was loaded successfully");
        Assertions.assertTrue(
                amazonHomePage.isInputVisible(),
                "The page did not load correctly"
        );
    }

    public void searchForAndVerifyResults(String search) {
        LOGGER.info("Searching for: " + search);
        amazonHomePage.searchFor(search);
        int numberOfSearchResults = amazonSearchResultPage.waitForSearchResult();
        LOGGER.info("Number of results found in the first page: " + numberOfSearchResults);
        Assertions.assertTrue(
                numberOfSearchResults > 0,
                "there where no results found for the given search parameter"
        );
    }

}
