package Tests;

import Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class CreateContactTest extends TestBase {

    @Test
    public void testCreateContact() {
        //Беру список до изменения
        List<ContactData> before = app.contact().contactList();

        //Добавляю новый контакт
        ContactData contact = new ContactData(0, "CreateFirst2", "CreateMiddle3", "CreateLast4", "CreateNick", "CreateTitle", "CreateCompany");
        app.goTo().createContactPage();
        app.contact().createContact(contact);

        //Беру список после изменения
        List<ContactData> after = app.contact().contactList();

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
