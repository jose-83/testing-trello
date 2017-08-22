package pages;

import config.Config;
import org.openqa.selenium.TimeoutException;

/**
 * Represents login page
 */

public final class LoginPage extends BasePage {

    private static final String USERNAME_ID = "user";
    private static final String PASSWORD_ID = "password";
    private static final String LOGIN_BUTTON_ID = "login";

    public LoginPage() {
        driver.gotoUrl(Config.getTrelloLoginPageAddress());
    }

    public BoardsPage loginAs(String username, String password) {

        driver.writeById(USERNAME_ID, username);
        driver.writeById(PASSWORD_ID, password);
        driver.clickById(LOGIN_BUTTON_ID);

        return new BoardsPage();
    }

}
