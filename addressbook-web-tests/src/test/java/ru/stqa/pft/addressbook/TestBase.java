package ru.stqa.pft.addressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestBase {
    protected WebDriver wd;
    protected boolean acceptNextAlert = true;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook");
        login("admin", "secret");
        acceptNextAlert = true;
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void returnGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    protected void submitCreateNewGroup() {
        wd.findElement(By.name("submit")).click();
    }

    protected void fillGropForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initCreateNewGroup() {
        wd.findElement(By.name("new")).click();
    }

    protected void openGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    protected void returnHomePage() {
      wd.findElement(By.linkText("home")).click();
    }

    protected void submitCreateNewUser() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillFormNewUser(UserData userData) {
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

    protected void initCreateNewUser() {
      wd.findElement(By.linkText("add new")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        logout();
        wd.quit();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void deleteSelectedGroup() {
      wd.findElement(By.xpath("//input[@value='Delete group(s)']")).click();
    }

    protected void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

    protected String closeAlertAndGetItsText() {
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

    protected void confirmDeleteUsers() {
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    protected void deleteSelectedUsers() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    protected void selectUser() {
      wd.findElement(By.name("selected[]")).click();
    }
}
