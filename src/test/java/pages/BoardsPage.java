package pages;

/**
 * Represents Boards page or trello homepage
 */

public final class BoardsPage extends BasePage {

    private static final String BOARD_CREATION_LINK_XPATH = "//a[child::span[text()[contains(.,'Create new board')]]]";
    private static final String BOARD_NEW_TITLE_INPUT_ID = "boardNewTitle";
    private static final String CREATE_BUTTON_XPATH = "//input[@value='Create']";
    private static final String BOARD_WITH_NAME_DRAFT_XPATH = "//li[descendant::span[@title='%s'] and @class='boards-page-board-section-list-item']";
    private static final String BOARD_MENU_CONTAINER_XPATH = "//div[@class='board-menu-container']";
    private static final String BOARD_LINK_DRAFT_XPATH = "//a[descendant::span[@title='%s'] and @style]";

    public BoardsPage() {
        driver.waitForElementClickableByXPath(HEADER_LOGO_XPATH);
    }

    public BoardPage createBoard(String boardName) {
        if (!this.isBoardAvailable(boardName)) {
            driver.clickByXpath(BOARD_CREATION_LINK_XPATH);
            driver.waitForElementClickableById(BOARD_NEW_TITLE_INPUT_ID);
            driver.writeById(BOARD_NEW_TITLE_INPUT_ID, boardName);
            driver.clickByXpath(CREATE_BUTTON_XPATH);
            driver.waitForElementShownByXPath(BOARD_MENU_CONTAINER_XPATH);
            return new BoardPage();
        } else {
            return this.goToBoard(boardName);
        }
    }

    public boolean isBoardAvailable(String boardName) {
        return !driver.findListByXPath(String.format(BOARD_WITH_NAME_DRAFT_XPATH, boardName)).isEmpty();
    }

    public BoardPage goToBoard(String boardName) {
        driver.clickByXpath(String.format(BOARD_LINK_DRAFT_XPATH, boardName));
        driver.waitForElementShownByXPath(BOARD_MENU_CONTAINER_XPATH);
        return new BoardPage();
    }

}
