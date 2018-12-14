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
        List<WebElement> elements = driver.findElements(By.xpath("//td[@class='center']/input[@type='checkbox']"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.xpath("//input[@name='selected[]']")).getAttribute("value"));
            ContactData contact = new ContactData(id, name, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
