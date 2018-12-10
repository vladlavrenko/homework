package Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToGroupPage() {
        if (!isElementPresent(By.linkText("group page"))) {
            return;
        } else {
            click(By.linkText("group page"));
        }
    }

    public void goToContactsCreation() {
       click(By.linkText("add new"));
    }

    public void goToGroups() {
        if (isElementPresent(By.tagName("h1"))
                && driver.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        } else {
            click(By.linkText("groups"));
        }

    }

    public void goToHomePage() {
        if (!isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));
        }
    }
}