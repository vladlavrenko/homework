package Appmanager;

import Model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void fillGroupFields(GroupData groupData) {
        type(By.name("group_name"), groupData.getGroupName());
        type(By.name("group_header"), groupData.getGroupHeader());
        type(By.name("group_footer"), groupData.getGroupFooter());
    }


    public void returnToGroupPage() {
        if (!isElementPresent(By.linkText("group page"))) {
            return;
        } else {
            click(By.linkText("group page"));
        }
    }

    public void submitGroupCreationForm() {
        click(By.name("submit"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void checkGroup(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupEdition() {
        click(By.name("edit"));
    }

    public void initGroupDeletion() {
        click(By.name("delete"));
    }

    public void submitGroupEditionForm() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupFields(group);
        submitGroupCreationForm();
        returnToGroupPage();
    }

    public void edit(int index, GroupData group) {
        checkGroup(index);
        initGroupEdition();
        fillGroupFields(group);
        submitGroupEditionForm();
        returnToGroupPage();
    }

    public int groupAmount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public void delete(int index) {
        checkGroup(index);
        initGroupDeletion();
        returnToGroupPage();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//span[contains(@class,'group')]"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
