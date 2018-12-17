package Model;

import java.util.Objects;

public class ContactData {
    //Переменные ContactData - данные, которые есть у контактов
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String company;

    //Конструктор ContactData если есть ID
    public ContactData(int id, String firstName, String middleName, String lastName, String nickName, String title, String company) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
    }

    //Конструктор ContactData если нет ID
    public ContactData(String firstName, String middleName, String lastName, String nickName, String title, String company) {
        this.id = 0;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
    }

    //Гетеры и сетеры, чтобы можно было брать эти данные из контактДата
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
    public void setId(int id) {
        this.id = id;
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
