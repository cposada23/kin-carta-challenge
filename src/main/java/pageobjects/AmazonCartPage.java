package pageobjects;

import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CustomWaits;

import java.util.List;

public class AmazonCartPage {

    private static final int TIMEOUT = 20;
    private WebDriverWait wait;
    private WebDriver driver;

    @FindBy(id = "nav-cart-count-container")
    private WebElement goToCartButton;

    @FindAll(
            @FindBy(css = ".sc-list-item")
    )
    private List<WebElement> cartItems;

    public AmazonCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToCart() {
        goToCartButton.click();
    }

    public int numberOfItemsInTheCart() {
        try {
            CustomWaits.waitForNumberOfElementsToBeGreaterThanOne(cartItems, TIMEOUT);
            return cartItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

}
