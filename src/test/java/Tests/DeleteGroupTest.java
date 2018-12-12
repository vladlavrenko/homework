package Tests;

import Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteGroupTest extends TestBase {
    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().goToGroups();
        if (!app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("Test name", "Test header", "Test footer"));
        }
        List<GroupData> before = app.getGroupHelper().groupList();
        app.getGroupHelper().checkGroup(before.size() - 1);
        app.getGroupHelper().initGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().groupList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        for (int i = 0; i < after.size(); i++) {
            Assert.assertEquals(before.get(i), after.get(i));
        }

    }
}