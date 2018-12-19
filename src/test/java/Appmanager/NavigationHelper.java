package Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void createContactPage() {
        click(By.linkText("add new"));
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && driver.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        } else {
            click(By.linkText("groups"));
        }
    }
    public void homePage() {
        if (!isElementPresent(By.id("maintable"))) {
            click(By.xpath("//a[contains(.,'home')]"));
        }
    }
}