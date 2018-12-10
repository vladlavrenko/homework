package Appmanager;

import Model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void checkGroup() {
        click(By.name("selected[]"));
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
        return isElementPresent(By.name("selected"));
    }
}
