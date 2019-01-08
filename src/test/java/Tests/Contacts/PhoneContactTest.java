package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneContactTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().count() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("name")
                    .withHomePhone("123-132 (1231231) 123")
                    .withMobilePhone("123123123-12312313()()))")
                    .withWorkPhone("123123123 --  -- 12313123 1  ()()()"));
        } else if (app.contact().isPhonePresent().equals("")) {
            app.contact().create(new ContactData()
                    .withFirstName("name")
                    .withHomePhone("123-132 (1231231) 123")
                    .withMobilePhone("123123123-12312313()()))")
                    .withWorkPhone("123123123 --  -- 12313123 1  ()()()"));
        } else {
            return;
        }
    }

    @Test
    public void testCheckContactPhone() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(infoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter(s -> !s.equals(""))
                .map(PhoneContactTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}