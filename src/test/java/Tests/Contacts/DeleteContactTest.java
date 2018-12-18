package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public void checkPreconditions() {
        app.contact().goToHomePage();
        if (!app.contact().isContactPresent()) {
            app.contact().create(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
    }

    @Test
    public void testDeleteContact() {
        List<ContactData> before = app.contact().contactList();
        app.contact().delete(before);
        List<ContactData> after = app.contact().contactList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
