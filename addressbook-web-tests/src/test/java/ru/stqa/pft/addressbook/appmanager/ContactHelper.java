package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;
import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase{

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

    public void deleteSelectedUsers() {
      click(By.xpath("//input[@value='Delete']"));
    }


    public void initUserModifications() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitUserModifications() {
        click(By.name("update"));
    }
}
