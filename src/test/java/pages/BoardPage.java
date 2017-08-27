package pages;

import org.openqa.selenium.WebElement;

import java.util.List;

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

    private static final String ADD_LIST_LINK_XPATH = "//span[@class='placeholder js-open-add-list']";
    private static final String LIST_NAME_INPUT_XPATH = "//input[@class='list-name-input']";
    private static final String SAVE_LIST_BUTTON_XPATH = "//input[@value='Save']";
    private static final String LIST_COLUMN_BY_NAME_DRAFT_XPATH = "//div[@class='js-list list-wrapper' and descendant::h2[text()[contains(.,'%s')]]]";

    public String getBoardName() {
        return driver.findByXpath(BOARD_NAME_XPATH).getText();
    }

    public BoardsPage deleteBoard() {
        this.openBoardMenu();
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

    public BoardPage addList(String listName) {
        driver.clickByXpath(ADD_LIST_LINK_XPATH);
        driver.waitForElementClickableByXPath(LIST_NAME_INPUT_XPATH);
        driver.writeByXpath(LIST_NAME_INPUT_XPATH, listName);
        driver.clickByXpath(SAVE_LIST_BUTTON_XPATH);
        return this;
    }

    public boolean isListAvailable(String listName) {
        List<WebElement> list = driver.findListByXPath(String.format(LIST_COLUMN_BY_NAME_DRAFT_XPATH, listName));
        return !list.isEmpty() && list.get(0).isDisplayed();
    }
}
