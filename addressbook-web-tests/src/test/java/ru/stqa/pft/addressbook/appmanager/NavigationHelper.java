package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void openGroupPage() {
        if(isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
                    return;
        }
        click(By.linkText("groups"));
    }

    public void returnHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
      click(By.linkText("home"));
    }

    public void selectRandomCheckBox() {
      click(By.name("selected[]"));
    }

    public void acceptAllert() {
        wd.switchTo().alert().accept();
    }
}
