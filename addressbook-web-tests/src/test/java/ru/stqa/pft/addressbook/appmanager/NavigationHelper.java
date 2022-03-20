package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
    public NavigationHelper(WebDriver wd) {
        super(wd);
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
