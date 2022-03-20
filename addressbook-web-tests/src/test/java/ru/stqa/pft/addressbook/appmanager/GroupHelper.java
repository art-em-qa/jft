package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {
    private WebDriver wd;

    public GroupHelper(WebDriver wd) {
        this.wd = wd;
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

    public void deleteSelectedGroup() {
      wd.findElement(By.xpath("//input[@value='Delete group(s)']")).click();
    }
}
