package Tests;

import Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateGroupTest extends TestBase {

    @Test
    public void testCreateGroup() {
        app.getNavigationHelper().goToGroups();
        int before = app.getGroupHelper().groupAmount();
        app.getGroupHelper().createGroup(new GroupData("Test name", "Test header", "Test footer"));
        int after = app.getGroupHelper().groupAmount();
        Assert.assertEquals(after, before + 1);
    }

}