package pageobjects;

import dto.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CustomActions;
import utils.CustomWaits;
import utils.exceptions.NumberOfItemsException;

import java.time.Duration;
import java.util.List;

public class AmazonSearchResultPage {

    private static final int TIMEOUT = 10;
    private static final Logger LOGGER = LogManager.getLogger(AmazonHomePage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='s-refinements']")
    private WebElement filtersContainer;

    // I use this input to ensure that the user can add the product to the cart
    // Because depending on the location of the user, the product may not be available for shipping
    @FindBy(xpath = "//*[@id='s-refinements']//*[contains(@id, 'free_shipping')]//input")
    private WebElement freeShippingInput;

    @FindAll(
        @FindBy(xpath = "//*[contains(@class, 's-result-item')]//*[contains(@class, 's-title-instructions-style')]//h2//a[contains(@class, 'a-link-normal')]")
    )
    private List<WebElement> resultItems;

    @FindBy(css = ".s-pagination-container")
    private WebElement paginationContainer;

    @FindBy(xpath = "//*[contains(@class, 's-main-slot s-result-list s-search-results')]")
    private WebElement searchResultContainer;

    public AmazonSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public int waitForSearchResult() {
        try {
            CustomWaits.waitForNumberOfElementsToBeGreaterThanOne(resultItems, TIMEOUT);
            return resultItems.size();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("An error occurred while tying to wait for the search results");
            return 0;
        }
    }

    public void selectFreeShipping() {
        wait.until(ExpectedConditions.visibilityOf(filtersContainer));
        CustomActions.jsClick(driver, freeShippingInput);
    }

    public void scrollToPaginationContainer() {
        CustomActions.scrollIntoView(driver, paginationContainer);
    }

    public void navigateToSecondPage() {
        paginationContainer.findElement(
                By.xpath("//*[contains(@class, 's-pagination-item') and text()=2]")
        ).click();
    }

    public Product selectItemNumber(int itemNumber) throws NumberOfItemsException {
        int numberOfItems = resultItems.size();
        if (numberOfItems < itemNumber) throw new NumberOfItemsException("No item number " + itemNumber + " found in the page");
        WebElement productElement = resultItems.get(itemNumber - 1);
        CustomActions.scrollIntoView(driver, productElement);
        wait.until(ExpectedConditions.elementToBeClickable(productElement));
        Product product = new Product(productElement.getText());
        productElement.click();
        return product;
    }

}
