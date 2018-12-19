package Tests.Contacts;

import Model.ContactData;
import Model.Contacts;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CreateContactTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
    }

    @Test
    public void testCreateContact() {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("Test name").withMiddleName("Test middle").withLastName("Test last");
        app.goTo().createContactPage();
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);

        Assert.assertEquals(after, before);
        assertThat(after, equalTo(before));
    }
}
