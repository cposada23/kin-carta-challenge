package controllers;

import dto.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageobjects.AmazonHomePage;
import pageobjects.AmazonProductDetailPage;
import pageobjects.AmazonSearchResultPage;

public class AmazonController {
    private static final Logger LOGGER = LogManager.getLogger(AmazonController.class);
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    private AmazonSearchResultPage amazonSearchResultPage;
    private AmazonProductDetailPage amazonProductDetailPage;

    public AmazonController(WebDriver driver) {
        this.driver = driver;
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResultPage = new AmazonSearchResultPage(driver);
        amazonProductDetailPage = new AmazonProductDetailPage(driver);
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
        amazonSearchResultPage.waitForSearchResult();
        amazonSearchResultPage.selectFreeShipping();
        int numberOfSearchResults = amazonSearchResultPage.waitForSearchResult();
        LOGGER.info("Number of results found in the first page: " + numberOfSearchResults);
        Assertions.assertTrue(
                numberOfSearchResults > 0,
                "there where no results found for the given search parameter"
        );
    }

    public void navigateToSecondPageOfSearchResults() {
        amazonSearchResultPage.scrollToPaginationContainer();
        amazonSearchResultPage.navigateToSecondPage();
        int numberOfSearchResults = amazonSearchResultPage.waitForSearchResult();
        Assertions.assertTrue(
                numberOfSearchResults > 0,
                "there where no results found for the given search parameter in the second page"
        );
    }

    public Product selectThirdItem() {
        try {
            Product selectedProduct = amazonSearchResultPage.selectItemNumber(3);
            LOGGER.info("Product selected: " + selectedProduct.getProductTitle());
            return selectedProduct;
        } catch (Exception e) {
            LOGGER.error("An error occurred selecting the third item in the result page. " + e.getMessage());
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    public void addProductToCart(Product selectedProduct) {
        String titleInDetailsPage = amazonProductDetailPage.getProductTitle();
        Assertions.assertEquals(
                titleInDetailsPage, selectedProduct.getProductTitle(),
                "The product title in the details page is not the expected title"
        );
    }

}
