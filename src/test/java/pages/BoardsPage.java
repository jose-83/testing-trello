package pages;

/**
 * Represents Boards page or trello homepage
 */

public final class BoardsPage extends BasePage {

    private static final String BOARD_CREATION_LINK_XPATH = "(//a[child::span[not(@class)] and parent::li])[1]";
    private static final String BOARD_NEW_TITLE_INPUT_ID = "boardNewTitle";
    private static final String CREATE_BUTTON_XPATH = "//input[@value='Create']";
    private static final String BOARD_WITH_NAME_DRAFT_XPATH = "//li[descendant::span[@title='%s'] and @class='boards-page-board-section-list-item']";
    private static final String BOARD_MENU_CONTAINER_XPATH = "//div[@class='board-menu-container']";
    private static final String BOARD_LINK_DRAFT_XPATH = "//a[descendant::span[@title='%s'] and @style]";
    private static final String TEAM_CREATION_LINK_XPATH = "//div[@class='boards-page-board-section u-clearfix']/a";
    private static final String TEAM_NAME_INPUT_ID = "org-display-name";
    private static final String TEAM_DESCRIPTION_ID = "org-desc";
    private static final String TEAM_BOARDS_PAGE_DRAFT_XPATH = "//div[@class='boards-page-board-section-header-options' and parent::div[child::h3[text()[contains(.,'%s')]]]]";
    private static final String TEAM_WITH_NAME_DRAFT_XPATH = "//div[@class='boards-page-board-section mod-no-sidebar' and descendant::h3[text()[contains(.,'%s')]]]";

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

    public TeamBoardsPage createTeam(String teamName, String description) {
        if (!this.isTeamAvailable(teamName)) {
            driver.clickByXpath(TEAM_CREATION_LINK_XPATH);
            driver.writeById(TEAM_NAME_INPUT_ID, teamName);
            driver.writeById(TEAM_DESCRIPTION_ID, description);
            driver.clickByXpath(CREATE_BUTTON_XPATH);
            return new TeamBoardsPage();
        } else {
            return this.goToTeamBoardsPage(teamName);
        }
    }

    public TeamBoardsPage goToTeamBoardsPage(String teamName) {
        driver.clickByXpath(String.format(TEAM_BOARDS_PAGE_DRAFT_XPATH, teamName));
        return new TeamBoardsPage();
    }

    public boolean isTeamAvailable(String teamName) {
        return !driver.findListByXPath(String.format(TEAM_WITH_NAME_DRAFT_XPATH, teamName)).isEmpty();
    }

}
