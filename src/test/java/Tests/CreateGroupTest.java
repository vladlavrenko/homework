package Tests;

import Model.GroupData;
import org.testng.annotations.Test;

public class CreateGroupTest extends TestBase {

    @Test
    public void testCreateGroup() throws Exception {
        app.getGroupHelper().goToGroups();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupFields(new GroupData("Test name", "Test header", "Test footer"));
        app.getGroupHelper().submitGroupCreationForm();
        app.getNavigationHelper().returnToGroupPage();
    }

}
