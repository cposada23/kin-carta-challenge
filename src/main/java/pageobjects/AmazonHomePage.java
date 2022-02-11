package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonHomePage {
    private static final int TIMEOUT = 10;
    private static final Logger LOGGER = LogManager.getLogger(AmazonHomePage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    // I use xpath instead of the input id, because the input id seems to be different for different screen sizes
    // So I just look for the input that is visible in the search bar
    @FindBy(xpath = "//*[contains(@class, 'nav-search-field')]/input")
    private WebElement searchInput;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));;

    }

    public boolean isInputVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchInput));
            return true;
        } catch (NoSuchElementException ex) {
            LOGGER.info(ex.getMessage());
            return false;
        }
    }

    public void searchFor(String search) {
        searchInput.sendKeys(search);
        searchInput.sendKeys(Keys.ENTER);
    }

}
