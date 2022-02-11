package pageobjects;

import com.google.common.base.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CustomWaits;

import java.time.Duration;
import java.util.List;

public class AmazonSearchResultPage {

    private static final int TIMEOUT = 10;
    private static final Logger LOGGER = LogManager.getLogger(AmazonHomePage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindAll(
        @FindBy(xpath = "//*[contains(@class, 's-result-item')]")
    )
    private List<WebElement> resultItems;

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
}
