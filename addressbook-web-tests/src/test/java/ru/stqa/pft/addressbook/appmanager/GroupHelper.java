package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public void openGroupPage() {
        click(By.linkText("groups"));
    }

    public void submitCreateNewGroup() {
        click(By.name("submit"));
    }

    public void fillGropForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initCreateNewGroup() {
        click(By.name("new"));
    }

    public void deleteSelectedGroup() {
        click(By.xpath("//input[@value='Delete group(s)']"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModifications() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        initCreateNewGroup();
        fillGropForm(group);
        submitCreateNewGroup();
        returnGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}