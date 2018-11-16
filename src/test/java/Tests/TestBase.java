package Tests;

import Appmanager.ApplicationManager;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeClass(alwaysRun = true)
    private void setUp() {
        app.init();
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() throws Exception {
        app.stop();
    }

    protected void submitContactCreationForm() {
        app.getGroupHelper().driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
    }

}
