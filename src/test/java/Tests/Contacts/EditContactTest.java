package Tests.Contacts;

import Model.ContactData;
import Model.Contacts;
import Tests.TestBase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class EditContactTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("name"));
        }
    }

    @Test
    public void testEditContact() {
        Contacts before = app.contact().all();
        ContactData editedContact = before.iterator().next();

        ContactData contact = new ContactData()
                .withFirstName("EditFirst")
                .withMiddleName("EditMiddle")
                .withLastName("EditLast")
                .withNickName("EditNick")
                .withTitle("EditTitle")
                .withCompany("EditCompany");
        app.contact().edit(editedContact);
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withEdited(contact, editedContact)));

    }

}