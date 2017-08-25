package utils.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

/**
 * Basic class for aggregating all wait methods
 */

final class Waiter {

    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_POLLING_MILLISECONDS = 400;

    private final FluentWait<WebDriver> fluentWaiter;

    Waiter(WebDriver driver) {
        this.fluentWaiter = new FluentWait<WebDriver>(driver);
        this.fluentWaiter.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(DEFAULT_POLLING_MILLISECONDS, TimeUnit.MILLISECONDS);
        this.fluentWaiter.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                .ignoring(InvalidElementStateException.class);
    }

    WebElement waitForElementClickable(By locator) {
        return fluentWaiter.until(ExpectedConditions.elementToBeClickable(locator));
    }

    WebElement waitForElementShown(By locator) {
        return fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    boolean waitForElementHidden(By locator) {
        return fluentWaiter.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
