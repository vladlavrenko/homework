package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact() {
        if (!app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        int before = app.getContactHelper().contactsAmount();
        app.getContactHelper().initContactEdition(before - 1);
        app.getContactHelper().fillContactsField(new ContactData("EditFirst", "EditMiddle","EditLast","EditNick","EditTitle", "EditCompany"));
        app.getContactHelper().submitContactEditionForm();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().contactsAmount();

        Assert.assertEquals(after, before);

    }
}
