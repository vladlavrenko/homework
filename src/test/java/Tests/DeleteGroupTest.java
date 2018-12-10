package Tests;

import Model.GroupData;
import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {
    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().goToGroups();
        if (! app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("Test name", "Test header", "Test footer"));
        }
        app.getGroupHelper().checkGroup();
        app.getGroupHelper().initGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
    }
}