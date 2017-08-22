package pages;

/**
 * Represents logged out page
 */

public final class LoggedOutPage extends BasePage {

    private static final String LOGIN_BUTTON_XPATH = "//a[@href='/login']";

    public LoggedOutPage() {
        driver.waitForElementClickableByXPath(LOGIN_BUTTON_XPATH);
    }

}
