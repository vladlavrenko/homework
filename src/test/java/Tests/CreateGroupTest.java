package Tests;

import Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class CreateGroupTest extends TestBase {

    @Test
    public void testCreateGroup() {
        app.getNavigationHelper().goToGroups();
        List<GroupData> before = app.getGroupHelper().groupList();
        GroupData group = new GroupData("Create test name", "Create test header", "Create test footer");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().groupList();

        Assert.assertEquals(after.size(), before.size() + 1);

        int maxId = 0;
        for (GroupData g : after) {
            if (g.getId() > maxId) {
                maxId = g.getId();
            }
        }
        group.setId(maxId);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}