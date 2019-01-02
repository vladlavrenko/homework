package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneContactTest extends TestBase {

    @Test
    public void testCheckContactPhone() {
        app.goTo().homePage();
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
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
