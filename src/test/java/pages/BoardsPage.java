package pages;

/**
 * Represents logged out page
 */

public final class BoardsPage extends BasePage {

    private static final String HEADER_LOGO_XPATH = "//a[@class='header-logo js-home-via-logo']";

    public BoardsPage() {
        driver.waitForElementClickableByXPath(HEADER_LOGO_XPATH);
    }

}
