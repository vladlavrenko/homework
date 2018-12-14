package Tests;

import Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class EditGroupTest extends TestBase{
    @Test
    public void testEditGroup() {
        app.getNavigationHelper().goToGroups();
        if (! app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("Test name", "Test header", "Test footer"));
        }
        List<GroupData> before = app.getGroupHelper().groupList();
        app.getGroupHelper().checkGroup(before.size() - 1);
        app.getGroupHelper().initGroupEdition();
        GroupData group = new GroupData(before.get(before.size()-1).getId(), "Edit name", "Edit header", "Edit footer");
        app.getGroupHelper().fillGroupFields(group);
        app.getGroupHelper().submitGroupEditionForm();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().groupList();

        before.remove(before.size()-1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }
}
