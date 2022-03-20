package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
    public static boolean acceptNextAlert = true;
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public static String closeAlertAndGetItsText() {
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

    public void openGroupPage() {
        click(By.linkText("groups"));
    }

    public void returnHomePage() {
      click(By.linkText("home"));
    }

    public void selectRandomCheckBox() {
      click(By.name("selected[]"));
    }
}
