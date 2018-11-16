package Tests;

import Helpers.ContactData;
import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

    @Test
    public void testCreateContact() throws Exception {
        goToContacts();
        fillContactsField(new ContactData("First name", "Middle name", "Last name", "Nickname", "Title", "Company", "Address", "123", "456"));
        submitContactCreationForm();
    }

}
