package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("CreateFirst"));
        }
    }

    @Test
    public void testDeleteContact() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();

        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(deletedContact);
        Assert.assertEquals(after, before);
    }
}