package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmailContactTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withEmail("email1").withEmail2("email2").withEmail3("email3"));
        } else if (app.contact().isEmailPresent().equals("")) {
            app.contact().create(new ContactData().withEmail("email1").withEmail2("email2").withEmail3("email3"));
        } else {
            return;
        }
    }

    @Test
    public void testCheckContactEmail() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(emails(infoFromEditForm)));
    }

    public String emails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}