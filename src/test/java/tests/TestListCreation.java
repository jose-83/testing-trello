package tests;

import config.Config;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.BoardPage;
import pages.BoardsPage;
import pages.LoginPage;

public class TestListCreation extends AbstractTrelloSeleniumTest {

    private static final String admin = Config.getAdminName();
    private static final String password = Config.getAdminPass();
    private static final String boardName = "New Board";
    private static final String listName = "New List";
    private static BoardsPage homepage;

    @Before
    public void setUp() {
        LoginPage loginPage = new LoginPage();
        homepage = loginPage.loginAs(admin, password);
        homepage.createBoard(boardName).goToHomepage();
    }

    @After
    public void tearDown() {
        homepage.goToHomepage();
        homepage.goToBoard(boardName).deleteBoard();
        homepage.logout();
    }

    @Test
    public void testListCreation() {
        BoardPage boardPage = homepage.goToBoard(boardName);
        boardPage.addList(listName);
        boardPage.goToHomepage().goToBoard(boardName);

        Assert.assertTrue("The created list is not available on board.", boardPage.isListAvailable(listName));
    }

}
