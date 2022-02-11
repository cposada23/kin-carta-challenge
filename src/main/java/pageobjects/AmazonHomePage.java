package pageobjects;

import controllers.AmazonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @FindBy(xpath = "//*[contains(@class, 'nav-search-field')]/input")
    private WebElement searchInput;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isInputVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));;
            wait.until(ExpectedConditions.visibilityOf(searchInput));
            return true;
        } catch (NoSuchElementException ex) {
            LOGGER.info(ex.getMessage());
            return false;
        }
    }

}
