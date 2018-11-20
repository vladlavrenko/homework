package Tests;

import org.testng.annotations.Test;

public class EditContactTest extends  TestBase{
    @Test
    public void testEditContact() {
        app.getNavigationHelper().goToContactsCreation();

     }
}
