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
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("name"));
        }
    }

    @Test
    public void testEditContact() {
        List<ContactData> before = app.contact().list();
        ContactData editedContact = before.iterator().next();

        ContactData contact = new ContactData()
                .withFirstName("EditFirst")
                .withMiddleName("EditMiddle")
                .withLastName("EditLast")
                .withNickName("EditNick")
                .withTitle("EditTitle")
                .withCompany("EditCompany");
        app.contact().edit(editedContact);
        List<ContactData> after = app.contact().list();

        before.remove(editedContact);
        before.add(contact);
        Assert.assertEquals(after, before);

    }

}