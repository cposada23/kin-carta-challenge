package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public class WebDriverFactory {

    /**
     * Generate the correct driver accordingly to what is passed in the parameters
     * the default browser is chrome
     * @param browser String with the browser that we want to open only suporting for the moment chrome and firefox
     * @param headless in case that you want to run the test in headless mode
     * @return ChromeDriver or FirefoxDriver
     */
    public static WebDriver getWebDriver(String browser, boolean headless) {
        switch (browser.toLowerCase(Locale.ROOT)) {
            case "firefox":
                WebDriverManager.firefoxdriver().driverVersion("0.30.0").setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(headless);
                WebDriver firefoxDriver = new FirefoxDriver();
                firefoxDriver.manage().window().maximize();
                return firefoxDriver;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("headless");
                }
                WebDriver chromeDriver = new ChromeDriver(chromeOptions);
                chromeDriver.manage().window().maximize();
                return chromeDriver;
        }
    }
}
