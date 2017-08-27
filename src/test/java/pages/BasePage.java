package pages;

import org.openqa.selenium.WebElement;
import utils.selenium.Driver;

/**
 * Base abstract class that aggregate all basic page functions
 */

public abstract class BasePage {

    protected Driver driver;
    public static final String HEADER_LOGO_XPATH = "//a[@class='header-logo js-home-via-logo']";
    public static final String MEMBER_MENU_HEADER_XPATH = "//a[descendant::span[@class='member']]";
    public static final String LOGOUT_LINK_XPATH = "//a[@class='js-logout']";

    public BasePage() {
        driver = Driver.getWrappedDriver();
    }

    public BoardsPage goToHomepage() {
        driver.clickByXpath(HEADER_LOGO_XPATH);
        driver.refresh();
        return new BoardsPage();
    }

    public LoggedOutPage logout() {
        this.goToHomepage();
        driver.clickByXpath(MEMBER_MENU_HEADER_XPATH);
        WebElement logoutLink = driver.waitForElementClickableByXPath(LOGOUT_LINK_XPATH);
        logoutLink.click();
        return new LoggedOutPage();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
