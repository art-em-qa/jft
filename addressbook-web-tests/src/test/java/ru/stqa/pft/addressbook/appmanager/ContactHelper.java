package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase{
    private boolean acceptNextAlert = true;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitCreateNewUser() {
      click(By.name("submit"));
    }

    public void fillFormNewUser(UserData userData) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("lastname"),userData.getLastname());
        type(By.name("address"), userData.getAddress());
        type(By.name("work"), userData.getWorkphone());
        type(By.name("email"), userData.getEmail());
    }

    public void initCreateNewUser() {
      click(By.linkText("add new"));
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
      click(By.xpath("//input[@value='Delete']"));
    }
}
