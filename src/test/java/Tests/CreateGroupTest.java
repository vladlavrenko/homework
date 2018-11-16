package Tests;

import Helpers.GroupData;
import org.testng.annotations.Test;

public class CreateGroupTest extends TestBase{

    @Test
    public void testCreateGroup() throws Exception {
        goToGroups();
        initGroupCreation();
        fillGroupFields(new GroupData("Test name", "Test header", "Test footer"));
        submitGroupCreationForm();
        returnToGroupPage();
    }

}
