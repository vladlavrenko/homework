package Appmanager;

import Model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    //В класс передаю драйвер как параметр
    ContactHelper (WebDriver driver) {
        super(driver);
    }

    //Метод для заполнения полей формы
    public void fillContactsField(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
    }

    //Метод для нажимания на кнопку сейв
    public void submitContactCreationForm() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
    }

    //Метод для начала редактирования контакта
    public void initContactEdition(int index) {
        driver.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
    }

    //Жмаканье на другую кнопку сейв
    public void submitContactEditionForm() {
        click(By.xpath("//input[@value='Update']"));
    }

    //Жмаканье на кнопку удаления
    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    //мМтод который и начинает создавать контакт, и поля заполняет, и на кнопочки жмакает
    public void create(ContactData contact) {
        click(By.linkText("add new"));
        fillContactsField(contact);
        submitContactCreationForm();
        click(By.xpath("//a[contains(.,'home')]"));
    }

    public void delete(List<ContactData> before) {
        initContactEdition(before.size() - 1);
        submitContactDeletion();
        goToHomePage();
    }
    public void edit(List<ContactData> before, ContactData data) {
        initContactEdition(before.size() - 1);
        fillContactsField(data);
        submitContactEditionForm();
        goToHomePage();
    }

    public void goToHomePage() {
        if (!isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));
        }
    }

    //Проверка существует ли контакт, для обеспечения предусловия
    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    //Метод, который считает количество созданных контактов. Уже нинада, но пусть будет
    public int contactsAmount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    //А тут весело. Нам нужен список контактов, чтобы сравнивать че там после изменений.
    public List<ContactData> contactList() {

        //Создаем сам список, в который будем записывать то, что получается вытащить со страницы
        List<ContactData> contacts = new ArrayList<>();

        //Берем все строки таблицы и суем их в список. Entry - это чтобы без первой строки с заголовками столбцов
        List<WebElement> allRows = driver.findElements(By.xpath("//tr[@name = 'entry']"));

        //Начинаем построчно гонять и вытаскиывать нужную нам инфу (foreach не работает почему-то)
        for (int i = 0; i < allRows.size(); i++) {
            int counter = i+1;
            //Вытаскиваю фамилию
            String lastName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[2]", counter))).getText();
            //Вытаскиваю имя
            String firstName = allRows.get(i).findElement(By.xpath(String.format("//tr[@name = 'entry'][%s]/td[3]", counter))).getText();
            //Вытаскиваю ID
            int id = Integer.parseInt(allRows.get(i).findElement(By.tagName("input")).getAttribute("id"));
            //И хреначу это все в контакт...
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null,null);
            //...который потом хреначу в список коонтактов
            contacts.add(contact);
        }
        //Метод не жадный, списком контактов поделится
        return contacts;
    }
}