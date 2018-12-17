package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        }
        List<ContactData> before = app.getContactHelper().contactList();
        app.getContactHelper().initContactEdition(before.size() - 1);
        ContactData data = new ContactData(before.get(before.size()-1).getId(), "EditFirst", "EditMiddle","EditLast","EditNick","EditTitle", "EditCompany");
        app.getContactHelper().fillContactsField(data);
        app.getContactHelper().submitContactEditionForm();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().contactList();

        before.remove(before.size() -1);
        before.add(data);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }
}