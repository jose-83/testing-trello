package config;

/**
 * The config file contains constants and parameters for the test launching
 */

public final class Config {

    private final static String TRELLO_ADMIN_NAME = System.getProperty("trello.admin");
    private final static String TRELLO_ADMIN_PASS = System.getProperty("trello.admin.pass");
    private final static String TRELLO_LOGIN_PAGE = System.getProperty("trello.login.page");
    private final static String BROWSER_NAME = System.getProperty("run.config.browser");

    public static String getAdminName() {
        return TRELLO_ADMIN_NAME;
    }

    public static String getAdminPass() {
        return TRELLO_ADMIN_PASS;
    }

    public static String getTrelloLoginPageAddress() {
        return TRELLO_LOGIN_PAGE;
    }

    public static String getBrowserName() {
        return BROWSER_NAME;
    }
}
