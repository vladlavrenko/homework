package Tests;

import Model.GroupData;
import org.testng.annotations.Test;

public class EditGroupTest extends TestBase{
    @Test
    public void testEditGroup() {
        app.getNavigationHelper().goToGroups();
        if (! app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("Test name", "Test header", "Test footer"));
        }
        app.getGroupHelper().checkGroup();
        app.getGroupHelper().initGroupEdition();
        app.getGroupHelper().fillGroupFields(new GroupData("EditGroupName", "EditGroupHeader", "EditGroupFooter"));
        app.getGroupHelper().submitGroupEditionForm();
        app.getGroupHelper().returnToGroupPage();
    }
}
