package Tests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {
    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkGroup();
        app.getGroupHelper().initGroupDeletion();
        app.getNavigationHelper().returnToGroupPage();
    }
}
