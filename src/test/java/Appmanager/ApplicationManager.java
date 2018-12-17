package Appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private WebDriver driver;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    //Инициализация запуска браузера. Проверется какой браузер я хочу запустить
    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.EDGE)) {
            driver = new EdgeDriver();
        } else {
            System.out.println("Incorrect browser");
        }
        //Говорю, что ждать элементы не дольше 1 секунды
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        driver.get("http://localhost/addressbook/");
        sessionHelper.login("admin", "secret");
        groupHelper = new GroupHelper(navigationHelper.driver);
        contactHelper = new ContactHelper(navigationHelper.driver);
    }

    public void stop() {
        driver.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }
}
