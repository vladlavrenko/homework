package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class CreateContactTest extends TestBase {

    @Test
    public void testCreateContact() {
        List<ContactData> before = app.getContactHelper().contactList();
        ContactData contact = new ContactData(0, "CreateFirst1", "CreateMiddle2", "CreateLast3", "CreateNick", "CreateTitle", "CreateCompany");
        app.getNavigationHelper().goToContactsCreation();
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().contactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        //        before.add()

        Assert.assertEquals(before.size(), after.size());
    }

}
