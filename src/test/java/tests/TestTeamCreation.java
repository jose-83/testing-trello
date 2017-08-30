package tests;

import config.Config;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.BoardsPage;
import pages.LoginPage;
import pages.TeamBoardsPage;

public class TestTeamCreation extends AbstractTrelloSeleniumTest {

    private static final String admin = Config.getAdminName();
    private static final String password = Config.getAdminPass();
    private static final String teamName = "New Team";
    private static final String teamDescription = "This a team for checking its functionality.";
    private static BoardsPage homepage;

    @Before
    public void setUp() {
        LoginPage loginPage = new LoginPage();
        homepage = loginPage.loginAs(admin, password);
    }

    @After
    public void tearDown() {
        homepage.goToHomepage();
        homepage.goToTeamBoardsPage(teamName).deleteTeam().goToHomepage();
        homepage.logout();
    }

    @Test
    public void testTeamCreation() {
        TeamBoardsPage teamPage = homepage.createTeam(teamName, teamDescription);
        Assert.assertEquals("Team name is not correct", teamName, teamPage.getTeamName());
        Assert.assertTrue("Team page was not properly loaded.", teamPage.allTabsAvailable());

        teamPage.goToHomepage();
        Assert.assertTrue("Team is not available on homepage.", homepage.isTeamAvailable(teamName));
    }

}
