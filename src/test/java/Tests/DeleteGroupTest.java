package Tests;

import Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {
    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().goToGroups();
        if (! app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("Test name", "Test header", "Test footer"));
        }
        int before = app.getGroupHelper().groupAmount();
        app.getGroupHelper().checkGroup();
        app.getGroupHelper().initGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().groupAmount();

        Assert.assertEquals(after, before-1);

    }
}