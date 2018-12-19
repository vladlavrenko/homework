package Appmanager;

import Model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Tests.TestBase.app;

public class ContactHelper extends HelperBase {

    //В класс передаю драйвер как параметр
    ContactHelper (WebDriver driver) {
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

    //Метод для начала редактирования контакта
    private void initContactEdition(int index) {
        driver.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
    }

    //Жмаканье на другую кнопку сейв
    private void submitContactEditionForm() {
        click(By.xpath("//input[@value='Update']"));
    }

    //Жмаканье на кнопку удаления
    private void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    //мМтод который и начинает создавать контакт, и поля заполняет, и на кнопочки жмакает
    public void create(ContactData contact) {
        click(By.linkText("add new"));
        fillContactsField(contact);
        submitContactCreationForm();
        click(By.xpath("//a[contains(.,'home')]"));
    }

    public void delete(int index) {
        initContactEdition(index);
        submitContactDeletion();
        app.goTo().homePage();
    }
    public void edit(int index, ContactData data) {
        initContactEdition(index);
        fillContactsField(data);
        submitContactEditionForm();
        app.goTo().homePage();
    }

    //Проверка существует ли контакт, для обеспечения предусловия. Уже не используется
    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    //Метод, который считает количество созданных контактов. Уже нинада, но пусть будет
    public int contactsAmount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    //А тут весело. Нам нужен список контактов, чтобы сравнивать че там после изменений.
    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> allRows = driver.findElements(By.xpath("//tr[@name = 'entry']"));
        for (int i = 0; i < allRows.size(); i++) {
            int counter = i+1;
            String lastName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[2]", counter))).getText();
            String firstName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[3]", counter))).getText();
            int id = Integer.parseInt(allRows.get(i).findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> allRows = driver.findElements(By.xpath("//tr[@name = 'entry']"));
        for (int i = 0; i < allRows.size(); i++) {
            int counter = i+1;
            String lastName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[2]", counter))).getText();
            String firstName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[3]", counter))).getText();
            int id = Integer.parseInt(allRows.get(i).findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}