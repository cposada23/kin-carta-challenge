package pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonProductDetailPage {

    private static final int TIMEOUT = 10;
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "productTitle")
    private WebElement productTitle;

    @FindBy(id = "submit.add-to-cart")
    private WebElement addToCartButton;

    @FindBy(id = "add-to-cart-confirmation-image")
    private WebElement confirmationImage;

    public AmazonProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }

    public boolean isAddToCartVisible() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public boolean validateItemWasSuccessfullyAddedToCart() {
        try {
            wait.until(ExpectedConditions.visibilityOf(confirmationImage));
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
