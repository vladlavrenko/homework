package Tests.Contacts;

import Model.ContactData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class CreateContactTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
    }

    @Test
    public void testCreateContact() {
        //Беру список до изменения
        List<ContactData> before = app.contact().list();

        //Добавляю новый контакт
        ContactData contact = new ContactData().withFirstName("Test name").withMiddleName("Test middle").withLastName("Test last");
        app.goTo().createContactPage();
        app.contact().create(contact);

        //Беру список после изменения
        List<ContactData> after = app.contact().list();

        //Присваиваю последней добавленной записи валидный ID
        int maxId = after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId();
        contact.setId(maxId);
        before.add(contact);

        //Сортирую списки по ID
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        after.sort(byId);
        before.sort(byId);

        //Сравниваю списки
        Assert.assertEquals(after, before);
    }

}
