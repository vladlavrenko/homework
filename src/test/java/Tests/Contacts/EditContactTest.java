package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class EditContactTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (!app.contact().isContactPresent()) {
            app.contact().create(new ContactData
                    ("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
    }

    @Test
    public void testEditContact() {
        List<ContactData> before = app.contact().contactList();
        ContactData data = new ContactData
                (before.get(before.size()-1).getId(), "EditFirst", "EditMiddle","EditLast","EditNick","EditTitle", "EditCompany");
        app.contact().edit(before, data);
        List<ContactData> after = app.contact().contactList();

        before.remove(before.size() -1);
        before.add(data);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }

}