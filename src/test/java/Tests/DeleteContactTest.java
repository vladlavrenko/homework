package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class DeleteContactTest extends  TestBase{
    @Test
    public void testDeleteContact() {
        app.goTo().homePage();
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        List<ContactData> before = app.contact().contactList();
        app.contact().initContactEdition(before.size() - 1);
        app.contact().submitContactDeletion();
        app.goTo().homePage();
        List<ContactData> after = app.contact().contactList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
