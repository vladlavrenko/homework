package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContactTest extends  TestBase{
    @Test
    public void testDeleteContact() {
        if (!app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        int before = app.getContactHelper().contactsAmount();
        app.getContactHelper().initContactEdition(before - 1);
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().contactsAmount();

        Assert.assertEquals(after, before - 1);

    }
}
