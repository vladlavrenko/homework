package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DetailedViewContactTest extends TestBase {
    static ContactData contact;

    @BeforeMethod
    public static void preconditions() {
        app.contact().all();
        contact = new ContactData().withFirstName("first").withLastName("last").withAddress("address")
                .withEmail("email").withEmail2("email2").withEmail3("email3")
                .withMobilePhone("12313asd ()--d4564").withHomePhone("546546").withWorkPhone("6465465464");
        app.contact().create(contact);
        contact.withId(app.contact().all().stream().mapToInt(ContactData::getId).max().getAsInt());
    }

    @Test
    public static void testDetailedViewContact() {
        ContactData infoFromDetailedView = app.contact().infoFromDetailedView(contact);
        ContactData infoFromEditFrom = app.contact().infoFromEditForm(contact);
        String infoFromDetailed = infoFromDetailedView.getFullName() + infoFromDetailedView.getAddress()
                + infoFromDetailedView.getMobilePhone() + infoFromDetailedView.getHomePhone() + infoFromDetailedView.getWorkPhone()
                + infoFromDetailedView.getEmail() + infoFromDetailedView.getEmail2() + infoFromDetailedView.getEmail3();
        String infoFromEdit = infoFromEditFrom.getFullName() + infoFromEditFrom.getAddress()
                + infoFromEditFrom.getMobilePhone() + infoFromEditFrom.getHomePhone() + infoFromEditFrom.getWorkPhone()
                + infoFromEditFrom.getEmail() + infoFromEditFrom.getEmail2() + infoFromEditFrom.getEmail3();

        Assert.assertEquals(infoFromDetailed, infoFromEdit);
    }

    @AfterMethod
    public static void postconditions() throws InterruptedException {
        app.contact().delete(contact);
    }
}
