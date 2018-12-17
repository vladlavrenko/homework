package Appmanager;

import Model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    ContactHelper (WebDriver driver) {
        super(driver);
    }

    public void fillContactsField(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
    }

    public void submitContactCreationForm() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
    }

    public void initContactEdition(int index) {
        driver.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
    }

    public void submitContactEditionForm() {
        click(By.xpath("//input[@value='Update']"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void createContact(ContactData contact) {
        click(By.linkText("add new"));
        fillContactsField(contact);
        submitContactCreationForm();
        click(By.xpath("//a[contains(.,'home')]"));
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public int contactsAmount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> contactList() {
        List<ContactData> contacts = new ArrayList<>();

        //беру строки таблицы
        List<WebElement> allRows = driver.findElements(By.xpath("//tr[@name = 'entry']"));
        //получаю данные ячеек
        for (int i = 0; i < allRows.size(); i++) {
            int counter = i+1;
            String lastName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[2]", counter))).getText();
            String firstName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[3]", counter))).getText();
            int id = Integer.parseInt(allRows.get(i).findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null,null);
            contacts.add(contact);

        }
        System.out.println(contacts);
        return contacts;
    }
}