package Tests;

import Model.ContactData;
import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

    @Test
    public void testCreateContact() {
        app.getNavigationHelper().goToContacts();
        app.getContactHelper().fillContactsField(new ContactData("1", "1","1","1","1","1"));
        app.getContactHelper().submitContactCreationForm();
    }

}
