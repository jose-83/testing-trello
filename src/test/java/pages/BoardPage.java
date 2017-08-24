package pages;

/**
 * Represents board page
 */

public final class BoardPage extends BasePage {

    private static final String BOARD_NAME_XPATH = "//a[not(@id)]/span[@class='board-header-btn-text']";
    private static final String BOARD_MENU_CONTAINER_XPATH = "//div[@class='board-menu-container']";
    private static final String SHOW_BOARD_MENU_LINK_XPATH = "//a[@class='board-header-btn mod-show-menu js-show-sidebar']";
    private static final String MORE_LINK_IN_MENU_XPATH = "//a[@class='board-menu-navigation-item-link js-open-more']";
    private static final String CLOSE_BOARD_LINK_XPATH = "//a[@class='board-menu-navigation-item-link js-close-board']";
    private static final String CLOSE_BOARD_BUTTON_XPATH = "//input[@value='Close']";
    private static final String BOARD_DELETION_LINK_XPATH = "//a[@class='quiet js-delete']";
    private static final String DELETE_BUTTON_XPATH = "//input[@value='Delete']";

    public String getBoardName() {
        return driver.findByXpath(BOARD_NAME_XPATH).getText();
    }

    public BoardsPage deleteBoard() {
        driver.clickByXpath(MORE_LINK_IN_MENU_XPATH);
        driver.clickByXpath(CLOSE_BOARD_LINK_XPATH);
        driver.clickByXpath(CLOSE_BOARD_BUTTON_XPATH);
        driver.clickByXpath(BOARD_DELETION_LINK_XPATH);
        driver.clickByXpath(DELETE_BUTTON_XPATH);
        return this.goToHomepage();
    }

    private BoardPage openBoardMenu() {
        if (!driver.isElementDisplayedByXpath(BOARD_MENU_CONTAINER_XPATH)) {
            driver.clickByXpath(SHOW_BOARD_MENU_LINK_XPATH);
            driver.waitForElementShownByXPath(BOARD_MENU_CONTAINER_XPATH);
        }
        return this;
    }
}
