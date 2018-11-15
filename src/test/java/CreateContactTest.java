package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UntitledTestCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://localhost/addressbook/");
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys("First name");
    driver.findElement(By.name("middlename")).click();
    driver.findElement(By.name("middlename")).clear();
    driver.findElement(By.name("middlename")).sendKeys("Middle nema");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys("Last name");
    driver.findElement(By.name("nickname")).click();
    driver.findElement(By.name("nickname")).clear();
    driver.findElement(By.name("nickname")).sendKeys("Nickname");
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("Title");
    driver.findElement(By.name("company")).click();
    driver.findElement(By.name("company")).clear();
    driver.findElement(By.name("company")).sendKeys("Company");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("Address");
    driver.findElement(By.name("home")).click();
    driver.findElement(By.name("home")).clear();
    driver.findElement(By.name("home")).sendKeys("123");
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys("456");
    driver.findElement(By.name("work")).click();
    driver.findElement(By.name("work")).clear();
    driver.findElement(By.name("work")).sendKeys("789");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("fax")).click();
    driver.findElement(By.name("fax")).clear();
    driver.findElement(By.name("fax")).sendKeys("0");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("qwe@qwe.qwe");
    driver.findElement(By.name("bday")).click();
    new Select(driver.findElement(By.name("bday"))).selectByVisibleText("1");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[3]")).click();
    driver.findElement(By.name("bmonth")).click();
    new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText("January");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[35]")).click();
    driver.findElement(By.name("byear")).click();
    driver.findElement(By.name("byear")).clear();
    driver.findElement(By.name("byear")).sendKeys("2000");
    driver.findElement(By.name("new_group")).click();
    new Select(driver.findElement(By.name("new_group"))).selectByVisibleText("test1");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Group:'])[1]/following::option[8]")).click();
    driver.findElement(By.name("address2")).click();
    driver.findElement(By.name("address2")).clear();
    driver.findElement(By.name("address2")).sendKeys("secondary");
    driver.findElement(By.name("phone2")).click();
    driver.findElement(By.name("phone2")).clear();
    driver.findElement(By.name("phone2")).sendKeys("secondary");
    driver.findElement(By.name("notes")).click();
    driver.findElement(By.name("notes")).clear();
    driver.findElement(By.name("notes")).sendKeys("secondary");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
