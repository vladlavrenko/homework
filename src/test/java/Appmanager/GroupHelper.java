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

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupFields(group);
        submitGroupCreationForm();
        returnToGroupPage();
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public int groupAmount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> groupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = driver.findElements(By.xpath("//span[contains(@class,'group')]"));
        for (WebElement element : elements) {
            String name = element.getText();
            GroupData group = new GroupData(name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
