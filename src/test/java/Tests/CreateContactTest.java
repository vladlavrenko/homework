package Tests;

import Model.ContactData;
import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

    @Test
    public void testCreateContact() {
        app.getNavigationHelper().goToContactsCreation();
        app.getContactHelper().fillContactsField(new ContactData("CreateFirst", "CreateMiddle","CreateLast","CreateNick","CreateTitle","CreateCompany"));
        app.getContactHelper().submitContactCreationForm();
        app.getNavigationHelper().goToHomePage();
    }

}
