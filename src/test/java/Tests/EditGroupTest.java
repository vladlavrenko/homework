package Tests;

import Model.GroupData;
import org.testng.annotations.Test;

public class EditGroupTest extends TestBase{
    @Test
    public void testEditGroup() {
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkGroup();
        app.getGroupHelper().initGroupEdition();
        app.getGroupHelper().fillGroupFields(new GroupData("EditGroupName", "EditGroupHeader", "EditGroupFooter"));
        app.getGroupHelper().submitGroupEditionForm();
        app.getNavigationHelper().returnToGroupPage();
    }
}
