package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteContactTest extends  TestBase{
    @Test
    public void testDeleteContact() {
        if (!app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        List<ContactData> before = app.getContactHelper().contactList();
        app.getContactHelper().initContactEdition(before.size() - 1);
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().contactList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(after, before);

    }
}
