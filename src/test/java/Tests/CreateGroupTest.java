package Tests;

import Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CreateGroupTest extends TestBase {

    @Test
    public void testCreateGroup() {
        app.getNavigationHelper().goToGroups();
        List<GroupData> before = app.getGroupHelper().groupList();
        app.getGroupHelper().createGroup(new GroupData("Create test name", "Create test header", "Create test footer"));
        List<GroupData> after = app.getGroupHelper().groupList();

        Assert.assertEquals(after.size(), before.size() + 1);
    }

}