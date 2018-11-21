package Tests;

import Model.GroupData;
import org.testng.annotations.Test;

public class EditGroupTest extends TestBase{
    @Test
    public void testEditGroup() {
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkGroup();
        app.getGroupHelper().initGroupEdition();
        app.getGroupHelper().fillGroupFields(new GroupData("Test edit name", "Test edit header", "Test edit footer"));
        app.getGroupHelper().submitGroupEditionForm();
        app.getNavigationHelper().returnToGroupPage();
    }
}
