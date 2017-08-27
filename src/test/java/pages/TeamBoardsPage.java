package pages;

/**
 * Represents Boards page for a team
 */

public final class TeamBoardsPage extends BasePage {

    private static final String BOARDS_TAB_XPATH = "//li[child::a[@data-tab='boards']]";
    private static final String MEMBERS_TAB_XPATH = "//li[child::a[@data-tab='members']]";
    private static final String SETTINGS_TAB_XPATH = "//li[child::a[@data-tab='settings']]";
    private static final String BUSINESS_CLASS_TAB_XPATH = "//li[child::a[@data-tab='business-class']]";
    private static final String TEAM_LOGO_XPATH = "//span[@class='profile-image-icon icon-lg icon-organization']";
    private static final String DELETE_TEAM_LINK_XPATH = "//a[@class='quiet-button']/span";
    private static final String DELETE_FOREVER_BUTTON = "//input[@value='Delete Forever']";
    private static final String TEAM_NAME_XPATH = "//h1[@class='u-inline']";


    public TeamBoardsPage() {
        driver.waitForElementClickableByXPath(TEAM_LOGO_XPATH);
    }

    public boolean allTabsAvailable() {
        return driver.isElementDisplayedByXpath(BOARDS_TAB_XPATH)
                && driver.isElementDisplayedByXpath(MEMBERS_TAB_XPATH)
                && driver.isElementDisplayedByXpath(SETTINGS_TAB_XPATH)
                && driver.isElementDisplayedByXpath(BUSINESS_CLASS_TAB_XPATH);
    }

    public String getTeamName() {
        return driver.findByXpath(TEAM_NAME_XPATH).getText();
    }

    public BoardsPage deleteTeam() {
        driver.clickByXpath(SETTINGS_TAB_XPATH);
        driver.clickByXpath(DELETE_TEAM_LINK_XPATH);
        driver.clickByXpath(DELETE_FOREVER_BUTTON);
        return new BoardsPage();
    }
}
