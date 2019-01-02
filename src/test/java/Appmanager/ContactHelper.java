package Appmanager;

import Model.ContactData;
import Model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Tests.TestBase.app;

public class ContactHelper extends HelperBase {

    //В класс передаю драйвер как параметр
    ContactHelper(WebDriver driver) {
        super(driver);
    }

    //Метод для заполнения полей формы
    private void fillContactsField(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
    }

    //Метод для нажимания на кнопку сейв
    private void submitContactCreationForm() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
    }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }


    private void initContactEditionById(int id) {
        driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    private void initContactDeletionById(int id) {
        driver.findElement(By.xpath(String.format("//input[@value='%s']", id))).click();
    }

    //Жмаканье на другую кнопку сейв
    private void submitContactEditionForm() {
        click(By.xpath("//input[@value='Update']"));
    }


    //Метод который и начинает создавать контакт, и поля заполняет, и на кнопочки жмакает
    public void create(ContactData contact) {
        click(By.linkText("add new"));
        fillContactsField(contact);
        submitContactCreationForm();
        contactCache = null;
        click(By.xpath("//a[contains(.,'home')]"));
    }

    public void delete(ContactData contact) throws InterruptedException {
        initContactDeletionById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
        Thread.sleep(200);
        contactCache = null;
        app.goTo().homePage();
    }

    public void edit(ContactData contact) {
        initContactEditionById(contact.getId());
        fillContactsField(contact);
        submitContactEditionForm();
        contactCache = null;
        app.goTo().homePage();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        Contacts contacts = new Contacts();
        List<WebElement> allRows = driver.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public void setContactCache(Contacts contactCache) {
        this.contactCache = contactCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactEditionById(contact.getId());
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        String homePhone = driver.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = driver.findElement(By.name("work")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId())
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
                .withMobilePhone(mobilePhone).withHomePhone(homePhone).withWorkPhone(workPhone);
    }
}