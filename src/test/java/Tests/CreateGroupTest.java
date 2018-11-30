package Tests;

import Model.GroupData;
import org.testng.annotations.Test;

public class CreateGroupTest extends TestBase {

    @Test
    public void testCreateGroup() {
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupFields(new GroupData("CreateGroupName", "CreateGroupHeader", "CreateGroupFooter"));
        app.getGroupHelper().submitGroupCreationForm();
        app.getNavigationHelper().returnToGroupPage();
    }

}