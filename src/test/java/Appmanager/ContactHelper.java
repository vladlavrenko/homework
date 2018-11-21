package Appmanager;

import Model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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

    public void initContactEdition() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='qwe@qwe.qwe'])[1]/following::img[2]"));
    }

    public void submitContactEditionForm() {
        click(By.name("update"));
    }

    public void submitContactDeletion() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='secondary'])[2]/following::input[3]"));
    }
}
