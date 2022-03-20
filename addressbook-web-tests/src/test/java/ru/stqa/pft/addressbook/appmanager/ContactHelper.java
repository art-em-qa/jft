package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

import static org.testng.Assert.assertTrue;

public class ContactHelper {
    private WebDriver wd;
    private boolean acceptNextAlert = true;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
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

    private String closeAlertAndGetItsText() {
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
}
