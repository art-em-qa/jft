package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void openGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void returnHomePage() {
      wd.findElement(By.linkText("home")).click();
    }

    public void selectRandomCheckBox() {
      wd.findElement(By.name("selected[]")).click();
    }
}
