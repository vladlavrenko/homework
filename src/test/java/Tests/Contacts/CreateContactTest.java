package Tests.Contacts;

import Model.ContactData;
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
        ContactData contact = new ContactData()
                .withFirstName("Test name").withMiddleName("Test middle").withLastName("Test last")
                .withEmail("email1").withEmail2("email2").withEmail3("email3")
                .withHomePhone("123-132 (1231231) 123").withMobilePhone("123123123-12312313()()))").withWorkPhone("123123123 --  -- 12313123 1  ()()()");
        app.goTo().createContactPage();
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
        before.add(contact);

        Assert.assertEquals(after, before);
        assertThat(after, equalTo(before));
    }
}