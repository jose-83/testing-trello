package utils.selenium;

import config.Config;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.SingleWebDriverPool;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Driver class provides useful wrappers to WebDriver's and Waiter's functions
 */

public final class Driver {
    private static final long DEFAULT_TIMEOUT = 100;

    private static Driver wrappedDriver;
    private static WebDriver webDriver;
    private Waiter waiter;

    private Driver(WebDriver driver) {
        this.waiter = new Waiter(driver);
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public static Driver getWrappedDriver(String browser) {
        if (wrappedDriver == null || webDriver == null) {
            webDriver = SingleWebDriverPool.DEFAULT.getDriver(browser);
            wrappedDriver = new Driver(webDriver);
        } else if (!isAlertShown()) {
            WebDriver newWebDriver = SingleWebDriverPool.DEFAULT.getDriver(browser);
            if (newWebDriver != webDriver) {
                webDriver = newWebDriver;
                wrappedDriver = new Driver(webDriver);
            }
        }

        return wrappedDriver;
    }

    public static Driver getWrappedDriver() {
        return getWrappedDriver(Config.getBrowserName());
    }

    private static Boolean isAlertShown() {
        try {
            Alert alert = new WebDriverWait(webDriver, 0, 01).until(ExpectedConditions.alertIsPresent());
            return alert != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void gotoUrl(String url) {
        webDriver.get(url);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public void refresh(){
        webDriver.navigate().refresh();
    }

    private void click(By locator) {
        waiter.waitForElementClickable(locator).click();
    }

    public void clickById(String id) {
        click(By.id(id));
    }

    public void clickByXpath(String xpath) {
        click(By.xpath(xpath));
    }

    /**
     * waitForElement* functions
     */

    public WebElement waitForElementClickableByXPath(String xpath) {
        return waiter.waitForElementClickable(By.xpath(xpath));
    }

    public WebElement waitForElementClickableById(String id) {
        return waiter.waitForElementClickable(By.id(id));
    }

    public WebElement waitForElementShownByXPath(String xpath) {
        return waiter.waitForElementShown(By.xpath(xpath));
    }

    /**
     * write functions
     */

    private void write(By locator, Object value) {
        WebElement el = waiter.waitForElementClickable(locator);
        el.click();
        el.clear();
        el.sendKeys(value.toString());
    }

    public void writeById(String id, Object value) {
        write(By.id(id), value.toString());
    }

    /**
     * find functions
     */

    private WebElement find(By locator) {
        return webDriver.findElement(locator);
    }

    public WebElement findByXpath(String path) {
        return find(By.xpath(path));
    }

    /**
     * findList functions
     */

    public List<WebElement> findListByXPath(String xpath) {
        return webDriver.findElements(By.xpath(xpath));
    }

    /**
     * isElementDisplayed functions
     */

    private boolean isElementPresent(By locator) {
        try {
            webDriver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    private boolean isElementDisplayed(By locator) {
        return isElementPresent(locator) && find(locator).isDisplayed();
    }

    public boolean isElementDisplayedById(String id) {
        return isElementDisplayed(By.id(id));
    }

    public boolean isElementDisplayedByName(String name) {
        return isElementDisplayed(By.name(name));
    }

    public boolean isElementDisplayedByXpath(String xpath) {
        return isElementDisplayed(By.xpath(xpath));
    }
}
