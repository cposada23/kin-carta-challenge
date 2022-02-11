package utils;

import com.google.common.base.Function;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class CustomWaits {
    public static void waitForNumberOfElementsToBeGreaterThanOne(List<WebElement> elements, final int TIMEOUT) throws TimeoutException {

        FluentWait<List<WebElement>> wait = new FluentWait<>(elements);
        wait.ignoring(NoSuchElementException.class);
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.withTimeout(Duration.ofSeconds(TIMEOUT));

        Function<List<WebElement>, Boolean> function = new Function<List<WebElement>, Boolean>() {
            @Override
            public @Nullable Boolean apply(@Nullable List<WebElement> webElements) {
                return webElements.size() > 0;
            }
        };
        wait.until(function);
    }
}
