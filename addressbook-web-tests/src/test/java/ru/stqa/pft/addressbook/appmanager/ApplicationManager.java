package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ApplicationManager {
    public WebDriver wd;
    public boolean acceptNextAlert = true;

    public void init() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook");
        login("admin", "secret");
        acceptNextAlert = true;
    }

    public void login(String username, String password) {
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void returnGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitCreateNewGroup() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillGropForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initCreateNewGroup() {
        wd.findElement(By.name("new")).click();
    }

    public void openGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void returnHomePage() {
      wd.findElement(By.linkText("home")).click();
    }

    public void submitCreateNewUser() {
      wd.findElement(By.name("submit")).click();
    }

    public void fillFormNewUser(UserData userData) {
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(userData.getFirstname());
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(userData.getLastname());
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(userData.getAddress());
      wd.findElement(By.name("work")).clear();
      wd.findElement(By.name("work")).sendKeys(userData.getWorkphone());
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(userData.getEmail());
    }

    public void initCreateNewUser() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void stop() {
        wd.quit();
    }

    public void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void deleteSelectedGroup() {
      wd.findElement(By.xpath("//input[@value='Delete group(s)']")).click();
    }

    public void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

    public String closeAlertAndGetItsText() {
      try {
        Alert alert = wd.switchTo().alert();
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

    public void confirmDeleteUsers() {
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void deleteSelectedUsers() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void selectUser() {
      wd.findElement(By.name("selected[]")).click();
    }
}
