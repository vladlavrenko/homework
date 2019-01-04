package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressContactTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withAddress("address"));
        } else if (app.contact().isAddressPresent().equals("")) {
            app.contact().create(new ContactData().withAddress("address"));
        } else {
            return;
        }
    }

    @Test
    public void testAddressContact() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(infoFromEditForm.getAddress()));
    }
}
