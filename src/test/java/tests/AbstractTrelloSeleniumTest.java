package tests;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import utils.MyLogger;

import java.util.logging.Logger;

public abstract class AbstractTrelloSeleniumTest {

    protected static Logger LOGGER;

    public AbstractTrelloSeleniumTest() {
        LOGGER = MyLogger.getInstance();
    }

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            LOGGER.info("TEST STARTING: " + description.getClassName() + " => " + description.getMethodName());
        }

        @Override
        protected void succeeded(Description description) {
            LOGGER.info("TEST SUCCEEDED!");
        }

        @Override
        protected void finished(Description description) {
        }

        @Override
        protected void failed(Throwable e, Description description) {
            LOGGER.warning("TEST FAILED with exception: " + MyLogger.MyFormatter.formatThrowable(e).toString());
        }
    };

}
