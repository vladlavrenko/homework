package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact() {
        app.goTo().homePage();
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        List<ContactData> before = app.contact().contactList();
        app.contact().initContactEdition(before.size() - 1);
        ContactData data = new ContactData(before.get(before.size()-1).getId(), "EditFirst", "EditMiddle","EditLast","EditNick","EditTitle", "EditCompany");
        app.contact().fillContactsField(data);
        app.contact().submitContactEditionForm();
        app.goTo().homePage();
        List<ContactData> after = app.contact().contactList();

        before.remove(before.size() -1);
        before.add(data);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }
}