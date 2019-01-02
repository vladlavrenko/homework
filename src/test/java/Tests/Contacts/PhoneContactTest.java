package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneContactTest extends TestBase {

    @Test
    public void testCheckContactPhone() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getHomePhone(), equalTo(infoFromEditForm.getHomePhone()));
        assertThat(contact.getMobilePhone(), equalTo(infoFromEditForm.getMobilePhone()));
        assertThat(contact.getWorkPhone(), equalTo(infoFromEditForm.getWorkPhone()));

    }


}
