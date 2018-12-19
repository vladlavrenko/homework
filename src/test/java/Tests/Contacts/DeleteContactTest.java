package Tests.Contacts;

import Model.ContactData;
import Model.Contacts;
import Tests.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("CreateFirst"));
        }
    }

    @Test
    public void testDeleteContact() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size()-1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}