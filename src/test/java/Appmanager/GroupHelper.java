package Appmanager;

import Model.GroupData;
import Model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    GroupHelper(WebDriver driver) {
        super(driver);
    }

    private void fillGroupFields(GroupData groupData) {
        type(By.name("group_name"), groupData.getGroupName());
        type(By.name("group_header"), groupData.getGroupHeader());
        type(By.name("group_footer"), groupData.getGroupFooter());
    }


    private void returnToGroupPage() {
        if (!isElementPresent(By.linkText("group page"))) {
            return;
        } else {
            click(By.linkText("group page"));
        }
    }

    private void submitGroupCreationForm() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    //Тут я выбирал группу по ее индексу в списке
    public void checkGroup(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    //А тут я начал выбирать группу по ее ID, который я беру прямо со страницы
    private void checkGroupById(int id) {
        driver.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
    }

    private void initGroupEdition() {
        click(By.name("edit"));
    }

    private void initGroupDeletion() {
        click(By.name("delete"));
    }

    private void submitGroupEditionForm() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupFields(group);
        submitGroupCreationForm();
        returnToGroupPage();
    }

    public void edit (GroupData group) {
        checkGroupById(group.getId());
        initGroupEdition();
        fillGroupFields(group);
        submitGroupEditionForm();
        returnToGroupPage();
    }

    public int groupAmount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    //Удаляю группу по ее содержимому
    public void delete(GroupData group) {
        checkGroupById(group.getId());
        initGroupDeletion();
        returnToGroupPage();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//span[contains(@class,'group')]"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    //Вытаскиваю набор групп, а не список, как раньше
    public Groups all() {
        Groups groups = new Groups();
        List<WebElement> elements = driver.findElements(By.xpath("//span[contains(@class,'group')]"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
