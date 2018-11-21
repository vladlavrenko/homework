package Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void goToContactsCreation() {
       click(By.linkText("add new"));
    }

    public void goToGroups() {
        click(By.linkText("groups"));
    }
}
