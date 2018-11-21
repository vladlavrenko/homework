package Tests;

import Model.ContactData;
import org.testng.annotations.Test;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact() {
        app.getContactHelper().initContactEdition();
        app.getContactHelper().fillContactsField(new ContactData("1", "1","1","1","1","1"));
        app.getContactHelper().submitContactEditionForm();
    }
}
