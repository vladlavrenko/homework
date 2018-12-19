package Model;

import java.util.Objects;

public class ContactData {
    //Переменные ContactData - данные, которые есть у контактов
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;
    private String title;
    private String company;

    //Гетеры, чтобы можно было брать эти данные из контактДата
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public int getId() {
        return id;
    }

    //Сетеры, они-же конструкторы для fluent'а
    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData setId(int id) {
        this.id = id;
        return this;
    }

    //Перевод из крякозябр на человеческий язык
    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    //Кастомные метод для стравнения объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    //От души хз, само генерируется вместе с сравнением
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
