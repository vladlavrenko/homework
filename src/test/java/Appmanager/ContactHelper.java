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
        click(By.xpath("//a[contains(.,'home')]"));
    }

    public void delete(ContactData contact) throws InterruptedException {
        initContactDeletionById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
        Thread.sleep(200);
        app.goTo().homePage();
    }

    public void edit(ContactData contact) {
        initContactEditionById(contact.getId());
        fillContactsField(contact);
        submitContactEditionForm();
        app.goTo().homePage();
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