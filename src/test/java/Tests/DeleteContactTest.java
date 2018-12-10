package Tests;

import Model.ContactData;
import org.testng.annotations.Test;

public class DeleteContactTest extends  TestBase{
    @Test
    public void testDeleteContact() {
        if (!app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        app.getContactHelper().initContactEdition();
        app.getContactHelper().submitContactDeletion();
     }
}
