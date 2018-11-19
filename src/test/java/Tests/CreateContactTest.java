package Tests;

import Model.ContactData;
import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

    @Test
    public void testCreateContact() {
        app.getNavigationHelper().goToContacts();
        app.getContactHelper().fillContactsField(new ContactData("1", "1","1","1","1","1","1","1","1"), "789", "0", "qwe@qwe.qwe", "1", "January", "2000", "test1", "secondary", "secondary", "secondary");
        app.getContactHelper().submitContactCreationForm();
    }

}
