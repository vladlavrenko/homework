package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

    @Test
    public void testCreateContact() {
        int before = app.getContactHelper().contactsAmount();
        app.getNavigationHelper().goToContactsCreation();
        app.getContactHelper().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        int after= app.getContactHelper().contactsAmount();

        Assert.assertEquals(after, before + 1);
    }

}
