package Tests;

import Model.ContactData;
import org.testng.annotations.Test;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact() {
        if (!app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        app.getContactHelper().initContactEdition();
        app.getContactHelper().fillContactsField(new ContactData("EditFirst", "EditMiddle","EditLast","EditNick","EditTitle", "EditCompany"));
        app.getContactHelper().submitContactEditionForm();
    }
}
