package Tests;

import Appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}