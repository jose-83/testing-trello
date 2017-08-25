package tests;

import config.Config;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.BoardPage;
import pages.BoardsPage;
import pages.LoginPage;

public class TestBoardCreation extends TrelloSeleniumTest {

    private static final String admin = Config.getAdminName();
    private static final String password = Config.getAdminPass();
    private static final String boardName = "New Board";
    private static BoardsPage homepage;

    @Before
    public void setUp() {
        LoginPage loginPage = new LoginPage();
        homepage = loginPage.loginAs(admin, password);
    }

    @After
    public void tearDown() {
        homepage.logout();
    }

    @Test
    public void testCreation() {
        BoardPage boardPage = homepage.createBoard(boardName);
        Assert.assertEquals("Creation process was not completely successful.", boardName, boardPage.getBoardName());

        boardPage.goToHomepage();
        Assert.assertTrue("The created board is not available on homepage.", homepage.isBoardAvailable(boardName));
    }

    @Test
    public void testDeletion() {
        BoardPage boardPage = homepage.createBoard(boardName);
        boardPage.deleteBoard();
        Assert.assertFalse("The board was not deleted.", homepage.isBoardAvailable(boardName));
    }

}
